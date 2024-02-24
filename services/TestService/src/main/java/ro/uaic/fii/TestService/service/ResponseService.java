package ro.uaic.fii.TestService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.TestService.exceptions.NotFoundException;
import ro.uaic.fii.TestService.model.Response;
import ro.uaic.fii.TestService.repository.ResponseRepository;

import java.util.List;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;

    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public List<Response> getAll() {
        return responseRepository.findAll();
    }

    public Response getById(Integer id) {
        return  responseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Response with id : " + id + " not found."));
    }

    public Response save(Response response) {
        return responseRepository.save(response);
    }

    public Response update(Integer id, Response response) {
        Response responseToUpdate = getById(id);
        responseToUpdate.setParticipantId(response.getParticipantId());
        responseToUpdate.setOrderNumber(response.getOrderNumber());
        responseToUpdate.setBaseQuestionId(response.getBaseQuestionId());
        responseToUpdate.setQuestion(response.getQuestion());
        responseToUpdate.setAnswer(response.getAnswer());
        responseToUpdate.setPoints(response.getPoints());
        responseToUpdate.setIpAddress(response.getIpAddress());
        responseToUpdate.setUpdateUid(response.getUpdateUid());
        return save(responseToUpdate);
    }

    public void delete(Integer id) {
        responseRepository.deleteById(id);
    }
}
