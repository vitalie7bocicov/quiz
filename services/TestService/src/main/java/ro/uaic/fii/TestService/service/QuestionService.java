package ro.uaic.fii.TestService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.model.Question;
import ro.uaic.fii.TestService.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question getById(Integer id) {
        return  questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question with id : " + id + " not found."));
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public Question update(Integer id, Question question) {
        Question questionToUpdate = getById(id);
        questionToUpdate.setDomainId(question.getDomainId());
        questionToUpdate.setContent(question.getContent());
        questionToUpdate.setActive(question.getActive());
        questionToUpdate.setNotes(question.getNotes());
        questionToUpdate.setUpdateUid(question.getUpdateUid());

        return questionRepository.save(questionToUpdate);
    }

    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }
}
