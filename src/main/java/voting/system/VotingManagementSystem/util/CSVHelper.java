package voting.system.VotingManagementSystem.util;

import jakarta.validation.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import voting.system.VotingManagementSystem.dto.StudentRequestDto;
import voting.system.VotingManagementSystem.entity.*;
import voting.system.VotingManagementSystem.exception.CSVFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CSVHelper {
    public static String TYPE = "text/csv";
    // Initialize Validator
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    // Utility to check if uploaded file is a CSV
    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType()) || "application/vnd.ms-excel".equals(file.getContentType());
    }

    public static List<Student> csvToStudents(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.builder()
                             .setHeader() // Uses first row as header
                             .setSkipHeaderRecord(true)
                             .setIgnoreHeaderCase(true)
                             .setTrim(true)
                             .build())) {

            List<Student> students = new ArrayList<>();
            int lineNumber = 1; // Track CSV row for detailed errors

            for (CSVRecord csvRecord : csvParser) {
                lineNumber++;
                StudentRequestDto dto = new StudentRequestDto();

                // Read and clean values safely
                dto.setSapId(cleanString(csvRecord.get("sapId")));
                dto.setName(cleanString(csvRecord.get("name")));
                dto.setEmail(cleanString(csvRecord.get("email")));

                String roleStr = cleanString(csvRecord.get("role"));
                if (!roleStr.isBlank()) dto.setRole(Role.fromString(roleStr));

                String programStr = cleanString(csvRecord.get("program"));
                if (!programStr.isBlank()) dto.setProgram(Program.fromString(programStr));

                String seasonStr = cleanString(csvRecord.get("season"));
                if (!seasonStr.isBlank()) dto.setSeason(Season.fromString(seasonStr));

                String statusStr = cleanString(csvRecord.get("status"));
                if (!statusStr.isBlank()) dto.setStudentStatus(StudentStatus.fromString(statusStr));

                String yearStr = cleanString(csvRecord.get("year"));
                if (!yearStr.isBlank()) dto.setYear(Year.of(Integer.parseInt(yearStr)));

                // 1. PROGRAMMATICALLY VALIDATE DTO
                Set<ConstraintViolation<StudentRequestDto>> violations = validator.validate(dto);

                if (!violations.isEmpty()) {
                    throw new ConstraintViolationException("Row " + (lineNumber - 1) + " validation failed", violations);
                }

                // 2. Add converted student if validation passes
                students.add(toStudent(dto));
            }

            return students;
        } catch (IOException e) {
            throw new CSVFileException("Failed to parse CSV file: " + e.getMessage(), CSVHelper.class.getSimpleName());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid enum value or integer format in CSV: " + e.getMessage());
        }
    }
    private static String cleanString(String input) {
        if (input == null) return "";
        return input.replace("\uFEFF", "")  // Remove UTF-8 Byte Order Mark
                .replaceAll("[\\r\\n]", "") // Remove carriage returns/newlines
                .trim();
    }

    private static Student toStudent(StudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setSapId(studentRequestDto.getSapId());
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setRole(studentRequestDto.getRole());
        student.setProgram(studentRequestDto.getProgram());
        student.setSeason(studentRequestDto.getSeason());
        student.setStudentStatus(studentRequestDto.getStudentStatus());
        student.setYear(studentRequestDto.getYear());
        return student;
    }
}
