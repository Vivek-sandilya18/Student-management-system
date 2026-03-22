package in.StudentManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.StudentManagement.Entity.Student;
import in.StudentManagement.Service.StudentService;

@Controller
public class StudentController {

    @Autowired	
    private StudentService service;
 
    @GetMapping("/")
    public String home() {
        return "login";   // 
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "students";
    }
  
    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }
    @GetMapping("/add-new-student")
    public String addStudent1(Model model) {
        model.addAttribute("student", new Student());
        return "add-new-student";
    }
 
 
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "edit-student";
    }
    
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }
}
