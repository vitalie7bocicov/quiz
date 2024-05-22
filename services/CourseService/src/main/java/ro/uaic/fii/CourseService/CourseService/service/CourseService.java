package ro.uaic.fii.CourseService.CourseService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CourseService.CourseService.dto.CourseInstructorDto;
import ro.uaic.fii.CourseService.CourseService.dto.CourseStudentGroupDto;
import ro.uaic.fii.CourseService.CourseService.dto.CourseTopicDto;
import ro.uaic.fii.CourseService.CourseService.dto.mapper.CourseMapper;
import ro.uaic.fii.CourseService.CourseService.dto.reqDto.CourseReqDto;
import ro.uaic.fii.CourseService.CourseService.dto.resDto.CourseResDto;
import ro.uaic.fii.CourseService.CourseService.exceptions.BadRequestException;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;
import ro.uaic.fii.CourseService.CourseService.repository.TopicRepository;
import ro.uaic.fii.CourseService.CourseService.repository.model.Course;
import ro.uaic.fii.CourseService.CourseService.repository.CourseRepository;
import ro.uaic.fii.CourseService.CourseService.repository.model.Topic;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    private final CourseMapper courseMapper;

    public CourseResDto save(CourseReqDto createDto) {
        Course savedCourse = courseRepository.save(courseMapper.dtoToEntity(createDto));
        return courseMapper.toDto(savedCourse);
    }

    public List<CourseResDto> getAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(courseMapper::toDto).toList();
    }

    public CourseResDto getById(int id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course", id));
        return courseMapper.toDto(course);
    }

    public CourseResDto update(int id, CourseReqDto updateDto) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course", id));
        existingCourse.setDomainId(updateDto.getDomainId());
        existingCourse.setSessionId(updateDto.getSessionId());
        existingCourse.setName(updateDto.getName());
        existingCourse.setAbbr(updateDto.getAbbr());
        existingCourse.setNotes(updateDto.getNotes());
        existingCourse.setUpdateUid(updateDto.getUserUid());
        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDto(updatedCourse);
    }

    public void deleteById(int id) {
        if (!courseRepository.existsById(id)) {
            throw new NotFoundException("Course", id);
        }
        courseRepository.deleteById(id);
    }

    public void addTopicToCourse(CourseTopicDto courseTopicDto) {
        Course course = courseRepository.findById(courseTopicDto.courseId())
                .orElseThrow(() -> new NotFoundException("Course", courseTopicDto.courseId()));
        Topic topic = topicRepository.findById(courseTopicDto.topicId())
                .orElseThrow(() -> new NotFoundException("Topic", courseTopicDto.topicId()));
        course.getTopics().add(topic);
        courseRepository.save(course);
    }

    public void addInstructorToCourse(CourseInstructorDto courseInstructorDto) {
        Course course = courseRepository.findById(courseInstructorDto.courseId())
                .orElseThrow(() -> new NotFoundException("Course", courseInstructorDto.courseId()));
        course.getInstructorIds().add(courseInstructorDto.instructorId());
        courseRepository.save(course);
    }

    public void addStudentGroupToCourse(CourseStudentGroupDto courseGroupDto) {
        Course course = courseRepository.findById(courseGroupDto.courseId())
                .orElseThrow(() -> new NotFoundException("Course", courseGroupDto.courseId()));
        course.getStudentGroupIds().add(courseGroupDto.studentGroupId());
        courseRepository.save(course);
    }
}
