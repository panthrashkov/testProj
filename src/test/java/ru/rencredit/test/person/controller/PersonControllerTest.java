package ru.rencredit.test.person.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.rencredit.test.person.service.PersonServiceImpl;
import ru.rencredit.test.person.view.PersonSave;
import ru.rencredit.test.person.view.PersonView;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

    private final Long ID = 1L;
    private final String NAME = "name";
    private final String SECOND_NAME = "secondName";
    private final String SURNAME = "surname";
    private final Date BIRTH_DATE = new GregorianCalendar(100 + 1900, 0, 1).getTime();

    private PersonView personView;
    private PersonView personErrorView;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonServiceImpl personService;

    @Before
    public  void setUp(){
        personView = new PersonView(ID, NAME, SECOND_NAME, SURNAME, BIRTH_DATE);
        personErrorView = new PersonView(ID, "", "", "", BIRTH_DATE);
    }
    /**
     * Test get list persons
     */
    @Test
    public void getAllTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/person/all")
                .contentType(MediaType.APPLICATION_JSON);

        List<PersonView> personsView = Collections.singletonList(personView);

        when(personService.getAll()).thenReturn(personsView);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[{\"name\":\"name\",\"secondName\":\"secondName\",\"surname\":\"surname\",\"birthDate\":\"1999-12-31T21:00:00.000+0000\",\"id\":1}]"))
                .andReturn();
    }

    /**
     * Test get person
     */
    @Test
    public void loadById() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/person/" + ID)
                .contentType(MediaType.APPLICATION_JSON);

        when(personService.loadById(ID)).thenReturn(personView);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print())
                .andExpect(content().json("{\"name\":\"name\",\"secondName\":\"secondName\",\"surname\":\"surname\",\"birthDate\":\"1999-12-31T21:00:00.000+0000\",\"id\":1}"))
                .andReturn();
    }

    /**
     * Test update person
     */
    @Test
    public void update() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/person/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personToJson(personView));

        doNothing().when(personService).update(personView);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"result\":\"success\"}"))
                .andReturn();
    }

    /**
     * Test update person client error
     */
    @Test
    public void updateError() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/person/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personToJson(personErrorView));

        doNothing().when(personService).update(personErrorView);

        mvc.perform(request)
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
    }


    /**
     * Test save person
     */
    @Test
    public void save() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/person/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personToJson(personView));

        doNothing().when(personService).add(personView);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"result\":\"success\"}"))
                .andReturn();
    }

    /**
     * Test save person client error
     */
    @Test
    public void saveError() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/person/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personToJson(personErrorView));

        doNothing().when(personService).add(personErrorView);

        mvc.perform(request)
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
    }



    private String personToJson(PersonSave person){
        try {
            return new ObjectMapper().writeValueAsString(person);
        } catch (JsonProcessingException e) {
            return Strings.EMPTY;
        }
    }


}
