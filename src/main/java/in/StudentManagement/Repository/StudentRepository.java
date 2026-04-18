package in.StudentManagement.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.StudentManagement.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT SUM(s.fees) FROM Student s")
    Integer getTotalFees();

    @Query("SELECT COUNT(DISTINCT s.course) FROM Student s")
    Long getTotalCourses();

    List<Student> findTop5ByOrderByIdDesc();

    List<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCourseContainingIgnoreCase(
            String name, String email, String course);

    Page<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCourseContainingIgnoreCase(
            String name, String email, String course, Pageable pageable);

   
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    
    Student findByEmail(String email);
    Student findByPhone(String phone);
}