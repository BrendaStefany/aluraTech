package br.com.brendaStefany.aluraTech.controller;

import br.com.brendaStefany.aluraTech.domain.Courses;
import br.com.brendaStefany.aluraTech.dto.courses.CoursesDTO;
import br.com.brendaStefany.aluraTech.dto.courses.CoursesOutboundDTO;
import br.com.brendaStefany.aluraTech.dto.courses.CoursesOutboundListPageDTO;
import br.com.brendaStefany.aluraTech.service.CoursesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/courses")
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @PostMapping()
    public ResponseEntity<CoursesOutboundDTO> addNewCourse(@RequestBody @Valid Courses course){
        CoursesOutboundDTO courseDTO = coursesService.addNewCourse(course);
        return new ResponseEntity<>(courseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/inactivate/{code}")
    public ResponseEntity<String> inactivateCourse(@PathVariable String code) {
        coursesService.inactivateCourse(code);
        return ResponseEntity.ok("Course '" + code + "' successfully deactivated");
    }

    @GetMapping()
    public Page<CoursesOutboundListPageDTO.CoursesDataOutboundList> getCourses(@RequestParam(required = false) String status,
                                               @RequestParam(required = false) String fieldOrder,
                                               @RequestParam(required = false) String order, @RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) Integer size) {

        if (fieldOrder == null)
            fieldOrder = "name";
        Sort ordering =
        this.createOrdering(fieldOrder, order);
        Pageable pagination = createPagination(page, size, ordering);

        Page<CoursesOutboundListPageDTO.CoursesDataOutboundList> coursesPage = coursesService.findCourses(status, pagination);

       return coursesPage;
    }

    private Sort createOrdering(String fieldOrder, String order) {
        Sort sort;
        if (order != null && order.equalsIgnoreCase("ASC")) {
            sort = Sort.by(fieldOrder).ascending();
        } else {
            sort = Sort.by(fieldOrder).descending();
        }
        return sort;
    }

    private Pageable createPagination(Integer page, Integer size, Sort ordering) {
        Pageable pagination;
        if (size != null && page != null) {
            pagination = PageRequest.of(page, size, ordering);
        } else {
            pagination = PageRequest.of(0, 10, ordering);
        }
        return pagination;
    }

}
