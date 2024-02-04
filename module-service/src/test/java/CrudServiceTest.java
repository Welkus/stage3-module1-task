import com.mjc.school.repository.NewsCrud;
import com.mjc.school.repository.NewsCrudImpl;
import com.mjc.school.repository.RepositoryAccess;
import com.mjc.school.service.CrudService;
import com.mjc.school.service.NewDTOBuilder;
import com.mjc.school.service.NewsDTO;
import com.mjc.school.service.NewsMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrudServiceTest {

    @Test
    public void testFindNewsById(){
        RepositoryAccess ra = new RepositoryAccess();
        NewsCrud newsCrud = new NewsCrudImpl(ra);
        CrudService crudService = new CrudService(newsCrud, NewsMapper.INSTANCE);
        NewsDTO newsDTO =  crudService.findNewsById(2L);
        assertEquals(2,newsDTO.getId(),"News id: "+ newsDTO.getId());

    }

    @Test
    public void testFindByIDAuthor(){
        RepositoryAccess ra = new RepositoryAccess();
        NewsCrud newsCrud = new NewsCrudImpl(ra);
        CrudService crudService = new CrudService(newsCrud, NewsMapper.INSTANCE);
        NewsDTO newsDTO = crudService.findNewsById(1L);
        assertEquals("Hangman's House",newsDTO.getTitle());

    }

    @Test
    public void testFindAll(){
        RepositoryAccess ra = new RepositoryAccess();
        NewsCrud newsCrud = new NewsCrudImpl(ra);
        CrudService crudService = new CrudService(newsCrud, NewsMapper.INSTANCE);

        List<Long> allList = crudService.findAllNews().stream().map(NewsDTO::getAuthorId).toList();

        List<Long> authorIds = new ArrayList<>(Arrays.asList(7L, 12L, 1L, 4L, 2L, 8L, 9L, 11L, 2L, 7L, 6L, 10L, 3L, 4L, 11L, 1L, 10L, 9L, 5L, 12L));

        assertEquals(allList,authorIds);
    }

    @Test
    public void updateTest(){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;


        RepositoryAccess ra = new RepositoryAccess();
        NewsCrud newsCrud = new NewsCrudImpl(ra);
        CrudService crudService = new CrudService(newsCrud, NewsMapper.INSTANCE);
        NewsDTO newsDTO =  new NewDTOBuilder().setId(1).setContent("this text").setTitle("Title").setAuthorId(5).build();

        NewsDTO testingDTO = new NewDTOBuilder().setId(1)
                .setContent("this text")
                .setTitle("Title")
                .setAuthorId(5)
                .setCreationDate(LocalDateTime.parse("2021-08-05T03:07:16Z",formatter))
                .build();

        NewsDTO returned = crudService.updateNews(newsDTO);

        assertEquals(testingDTO.getContent(),returned.getContent());
        assertEquals(testingDTO.getTitle(),returned.getTitle());
        assertEquals(testingDTO.getAuthorId(),returned.getAuthorId());
        assertEquals(testingDTO.getCreateDate(),returned.getCreateDate());
    }

}
