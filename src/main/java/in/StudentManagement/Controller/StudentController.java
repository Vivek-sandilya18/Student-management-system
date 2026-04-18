package in.StudentManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.StudentManagement.Entity.Student;
import in.StudentManagement.Service.StudentService;

import org.springframework.data.domain.Page;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Controller
public class StudentController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StudentService service;

    private String generatedOtp;
    private Student tempStudent;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/students")
    public String students(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           Model model) {

        Page<Student> studentPage = service.searchStudentsPaginated(keyword, page, 5);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        model.addAttribute("totalStudents", service.getTotalStudents());
        model.addAttribute("totalFees", service.getTotalFees());
        model.addAttribute("totalCourses", service.getTotalCourses());
        model.addAttribute("recentStudents", service.getRecentStudents());
        model.addAttribute("recentDeleted", service.getRecentlyDeleted());

        return "students";
    }

    @GetMapping("/students-data")
    @ResponseBody
    public Page<Student> getStudentsAjax(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String keyword) {

        return service.searchStudentsPaginated(keyword, page, 5);
    }

    @GetMapping("/add-new-student")
    public String addnewStudent(Model model) {
        model.addAttribute("student", new Student());
        return "add-new-student";
    }

    @GetMapping("/add-student")
    public String addnewStudent1(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/sendOtp")
    public String sendOtp(@ModelAttribute Student student, Model model) {

        if (service.isDuplicate(student)) {
            model.addAttribute("error", "❌ Student already exists!");
            return "add-new-student";
        }

        tempStudent = student;

        generatedOtp = String.valueOf((int)(Math.random() * 9000) + 1000);
        System.out.println("🔥 Generated OTP: " + generatedOtp);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(student.getEmail());
            message.setSubject("Your Verification OTP - Student Management System");
            message.setText("Hello " + student.getName() + ",\n\nYour OTP for registration is: " + generatedOtp);

            mailSender.send(message);

        } catch (Exception e) {
            model.addAttribute("error", "Failed to send OTP");
            return "add-new-student";
        }

       
        return "otp-page";
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String otp, Model model) {

        if (otp.equals(generatedOtp)) {
            service.saveStudent(tempStudent);
            return "redirect:/students";
        } else {
            model.addAttribute("error", "❌ Invalid OTP");
            return "otp-page";
        }
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "edit-student";
    }

   
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student, Model model) {

        if (service.isDuplicate(student)) {
            model.addAttribute("error", "❌ Student already exists!");
            return "redirect:/students";
        }

        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "redirect:/students";
    }
}