package spittr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import spittr.data.Spittle;
import spittr.data.SpittleRepository;
import spittr.web.SpittleController;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

public class SpittleControllerTest {
	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expecSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expecSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expecSpittles.toArray())));
	}
	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
	
	@Test
	public void shouldShowPagedSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(50);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(238900, 50)).thenReturn(expectedSpittles);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles?max=238900&count=50"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}
	
	@Test
	public void testSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle("hello", new Date());
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findOne((long) 12345)).thenReturn(expectedSpittle);
		
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spittles/12345"))
			.andExpect(view().name("spittle"))
			.andExpect(model().attributeExists("spittle"))
			.andExpect(model().attribute("spittle", expectedSpittle));
	}
}