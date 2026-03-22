package in.StudentManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.StudentManagement.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
