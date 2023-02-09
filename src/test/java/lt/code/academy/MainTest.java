package lt.code.academy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
;

import static org.junit.jupiter.api.Assertions.*;


class MainTest {

    private Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @Test
    void testMenu() {
        main.menu();
    }

    @Test
    void testCreateExamCorrectExamId() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals("1", exam.getExamId());
    }
    @Test
    void testCreateExamCorrectIfExamIdIsNull() {
        Exam exam = new Exam(null, "Java", "Homework", LocalDateTime.now());

        assertEquals(null, exam.getExamId());
    }

    @Test
    void testCreateExamCorrectExamName() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals("Java", exam.getExamName());
    }

    @Test
    void testCreateExamCorrectExamType() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals("Homework", exam.getExamType());
    }

    @Test
    void testCreateExamCorrectExamCreationYear() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals(LocalDateTime.now().getYear(), exam.getExamCreationDateTime().getYear());
    }

    @Test
    void testCreateExamCorrectExamCreationMonth() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals(LocalDateTime.now().getMonth(), exam.getExamCreationDateTime().getMonth());
    }

    @Test
    void testCreateExamCorrectExamCreationDay() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals(LocalDateTime.now().getDayOfMonth(), exam.getExamCreationDateTime().getDayOfMonth());
    }

    @Test
    void testCreateExamCorrectExamCreationHour() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals(LocalDateTime.now().getHour(), exam.getExamCreationDateTime().getHour());
    }

    @Test
    void testCreateExamCorrectExamCreationMinute() {
        Exam exam = new Exam("1", "Java", "Homework", LocalDateTime.now());

        assertEquals(LocalDateTime.now().getMinute(), exam.getExamCreationDateTime().getMinute());
    }
}