package ro.uaic.fii.CourseService.CourseService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.CourseService.CourseService.exceptions.BadRequestException;
import ro.uaic.fii.CourseService.CourseService.exceptions.NotFoundException;
import ro.uaic.fii.CourseService.CourseService.model.Section;
import ro.uaic.fii.CourseService.CourseService.repository.SectionRepository;

import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }


    public List<Section> getAll() {
        return sectionRepository.findAll();
    }

    public Section getById(Integer id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Section with ID: " + id + " not found."));
    }

    public Section save(Section section) {
        try {
            return sectionRepository.save(section);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public Section update(Integer id, Section section) {
        Section existingSection = getById(id);
        existingSection.setCourseId(section.getCourseId());
        existingSection.setName(section.getName());
        existingSection.setNotes(section.getNotes());
        existingSection.setOrderNumber(section.getOrderNumber());
        return sectionRepository.save(existingSection);
    }

    public void deleteById(Integer id) {
        sectionRepository.deleteById(id);
    }
}
