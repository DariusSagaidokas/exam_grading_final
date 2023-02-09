package lt.code.academy;

import java.time.LocalDateTime;

public class Exam {
    private String examId;
    private String examName;
    private String examType;
    private LocalDateTime examCreationDateTime;

    public Exam(String examId, String examName, String examType, LocalDateTime examCreationDateTime) {
        this.examId = examId;
        this.examName = examName;
        this.examType = examType;
        this.examCreationDateTime = examCreationDateTime;
    }

    public Exam() {
    }

    public String  getExamId() {
        return examId;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamType() {
        return examType;
    }

    public LocalDateTime getExamCreationDateTime() {
        return examCreationDateTime;
    }


    @Override
    public String toString() {
        return "Exam:" +
                "examId:" + examId +
                ", examName: " + examName +
                ", examType: " + examType +
                ", examCreationDateTime: " + examCreationDateTime +
                '}';
    }
}
