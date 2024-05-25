package ro.uaic.fii.TestService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.dto.mapper.QuestionMapper;
import ro.uaic.fii.TestService.dto.reqDto.QuestionReqDto;
import ro.uaic.fii.TestService.dto.resDto.QuestionResDto;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.repository.model.Question;
import ro.uaic.fii.TestService.repository.QuestionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    public List<QuestionResDto> getAll() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(questionMapper::toDto).toList();
    }

    public QuestionResDto getById(int id) {
        Question question =  questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question", id));
        return questionMapper.toDto(question);
    }

    public QuestionResDto save(QuestionReqDto dto) {
        Question savedQuestion = questionRepository.save(questionMapper.dtoToEntity(dto));
        return questionMapper.toDto(savedQuestion);
    }

    public QuestionResDto update(Integer id, QuestionReqDto dto) {
        Question questionToUpdate = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question", id));
        questionToUpdate.setDomainId(dto.getDomainId());
        questionToUpdate.setContent(dto.getContent());
        questionToUpdate.setActive(dto.isActive());
        questionToUpdate.setNotes(dto.getNotes());
        questionToUpdate.setUpdateUid(dto.getUserUid());

        Question updatedQuestion = questionRepository.save(questionToUpdate);
        return questionMapper.toDto(updatedQuestion);
    }

    public void delete(int id) {
        if (!questionRepository.existsById(id)) {
            throw new NotFoundException("Question", id);
        }
        questionRepository.deleteById(id);
    }

    public void addTopicToQuestion(int id, int topicId) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question", id));
        question.getTopicIds().add(topicId);
        questionRepository.save(question);
    }

    public void removeTopicFromQuestion(int id, int topicId) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question", id));
        question.getTopicIds().remove(topicId);
        questionRepository.save(question);
    }
}
