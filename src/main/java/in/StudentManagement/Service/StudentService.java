package in.StudentManagement.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.StudentManagement.Entity.Student;
import in.StudentManagement.Repository.StudentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    private List<Student> recentlyDeleted = new ArrayList<>();

    
    public boolean isDuplicate(Student student) {

        Student emailStudent = repo.findByEmail(student.getEmail());
        Student phoneStudent = repo.findByPhone(student.getPhone());

    
        if (emailStudent != null) {
            if (student.getId() == null ||
                !emailStudent.getId().equals(student.getId())) {
                return true;
            }
        }

     
        if (phoneStudent != null) {
            if (student.getId() == null ||
                !phoneStudent.getId().equals(student.getId())) {
                return true;
            }
        }

        return false;
    }

    public List<Student> searchStudents(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCourseContainingIgnoreCase(
                    keyword, keyword, keyword);
        } else {
            return repo.findAll();
        }
    }

    public Page<Student> getStudentsPaginated(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Page<Student> searchStudentsPaginated(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        if (keyword != null && !keyword.isEmpty()) {
            return repo
                .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCourseContainingIgnoreCase(
                        keyword, keyword, keyword, pageable);
        } else {
            return repo.findAll(pageable);
        }
    }

    public void saveStudent(Student student) {
        repo.save(student);
    }

    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        Student s = repo.findById(id).orElse(null);

        if (s != null) {
            recentlyDeleted.add(0, s);

            if (recentlyDeleted.size() > 5) {
                recentlyDeleted.remove(5);
            }

            repo.deleteById(id);
        }
    }

    public List<Student> getRecentlyDeleted() {
        return recentlyDeleted;
    }

    public long getTotalStudents() {
        return repo.count();
    }

    public int getTotalFees() {
        Integer total = repo.getTotalFees();
        return (total != null) ? total : 0;
    }

    public long getTotalCourses() {
        return repo.getTotalCourses();
    }

    public List<Student> getRecentStudents() {
        return repo.findTop5ByOrderByIdDesc();
    }
}