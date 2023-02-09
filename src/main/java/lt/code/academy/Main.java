package lt.code.academy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide path to exam file and to exam answers file");
            return;
        }
        String examFilePath = args[0];
        String examAnswersFilePath = args[1];
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Main main = new Main();

        String action;

        do {
            main.menu();
            action = scanner.nextLine();

            main.userAction(action, scanner, mapper, examFilePath, examAnswersFilePath);
        } while (!action.equals("3"));
    }

    void userAction(String action, Scanner scanner, ObjectMapper mapper, String examFilePath, String examAnswersFilePath) {
        switch (action) {
            case "1" -> generateExamFile(createExam(scanner), mapper, examFilePath, fillUpExamQuestions(scanner));
            case "2" ->
                    generateExamAnswersFile(examAnswersFilePath, generateAnswers(mapper, scanner, examFilePath, createStudent(scanner)));
            case "3" -> System.out.println("Exit");
            default -> System.out.println("Such action does not exist");
        }
    }

    void menu() {
        System.out.println("""
                1. Create exam
                2. Take an exam
                3. Exit
                """);
    }

    Exam createExam(Scanner scanner) {
        System.out.println("Enter exam ID:");
        String examId = scanner.nextLine();

        System.out.println("Enter exam name:");
        String examName = scanner.nextLine();

        System.out.println("Enter exam type:");
        String examType = scanner.nextLine();

        LocalDateTime examCreationDateTime = LocalDateTime.now();

        Exam exam = new Exam(examId, examName, examType, examCreationDateTime);
        return exam;
    }

    List<Question> fillUpExamQuestions(Scanner scanner) {
        List<Question> questions = new ArrayList<>();
        try {
            System.out.println("Enter the number of questions");
            int numbOfQuestions = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < numbOfQuestions; i++) {
                int count = i + 1;
                System.out.println("Enter the question:");
                String question = scanner.nextLine();

                System.out.println("Enter the amount of answers:");
                int numbOfAnswers = Integer.parseInt(scanner.nextLine());

                List<String> questionAnswers = new ArrayList<>();
                for (int j = 0; j < numbOfAnswers; j++) {
                    System.out.println("Enter the possible answer:");
                    String line = scanner.nextLine();
                    questionAnswers.add(line);
                }

                System.out.println("Enter correct answer:");
                String correctAnswer = scanner.nextLine();

                Question questionOne = new Question(count, question, questionAnswers, correctAnswer);
                questions.add(questionOne);
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input format, try again");
        }
        return questions;
    }

    void generateExamFile(Exam exam, ObjectMapper mapper, String examFilePath, List<Question> questions) {
        HashMap<String, List<Question>> examQuestions;
        File file = new File(examFilePath);
        try {
            if (file.exists()) {
                examQuestions = mapper.readValue(file, new TypeReference<>() {
                });
            } else {
                examQuestions = new HashMap<>();
            }
            examQuestions.put(exam.getExamId(), questions);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(file, examQuestions);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    Student createStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        String studentId = scanner.nextLine();

        System.out.println("Enter student name:");
        String studentName = scanner.nextLine();

        System.out.println("Enter student surname:");
        String studentSurname = scanner.nextLine();

        return new Student(studentId, studentName, studentSurname);
    }

    Map<String, Object> generateAnswers(ObjectMapper mapper, Scanner scanner, String examFilePath, Student student) {
        Map<String, List<Question>> exams = null;
        try {
            exams = mapper.readValue(new File(examFilePath), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("Could not read file");
        }

        System.out.println("Choose an exam by ID:");
        for (String exam : exams.keySet()) {
            System.out.println(exam);
        }

        String chosenExam = scanner.nextLine();
        List<Question> questions = exams.get(chosenExam);

        List<Map<String, Object>> answers = new ArrayList<>();
        int correctAnswers = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            for (String answer : question.getAnswers()) {
                System.out.println(answer);
            }
            System.out.println("Enter your answer:");
            String studentAnswer = scanner.nextLine();

            Map<String, Object> studentAnswers = new HashMap<>();
            studentAnswers.put("Question", question.getQuestionNumber());
            studentAnswers.put("Answer", studentAnswer);
            answers.add(studentAnswers);
            if (studentAnswer.equals(question.getCorrectAnswer())) {
                correctAnswers++;
            }
        }

        Map<String, Object> studentAnswers = new HashMap<>();
        studentAnswers.put("Student:", student);
        studentAnswers.put("Amount of correct answers:", correctAnswers);
        studentAnswers.put("Exam ID:", chosenExam);
        studentAnswers.put("Answers:", answers);

        return studentAnswers;
    }

    void generateExamAnswersFile(String examAnswersFilePath, Map<String, Object> studentAnswers) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(examAnswersFilePath);

        try {
            List<Map<String, Object>> saveStudentAnswers;
            if (file.exists()) {
                saveStudentAnswers = mapper.readValue(file, new TypeReference<>() {
                });
            } else {
                saveStudentAnswers = new ArrayList<>();
            }
            saveStudentAnswers.add(studentAnswers);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(file, saveStudentAnswers);
        } catch (IOException e) {
            System.out.println("Could not save to file");
        }
    }
}
