package productDetails.ProductDetails;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springboot.productDetails.ProductDetailsController;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.springboot.productDetails")
@SpringBootTest(classes={ProductDetailsController.class})
@AutoConfigureMockMvc
public class ProductDetailsTest {
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	public void getProductDataWithWrongURL() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/v1/productsasdfas")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().is4xxClientError());
	}

	
	@Test
	public void getProductDataWithDefaultAPI() throws Exception
	{
	  mvc.perform( MockMvcRequestBuilders
	      .get("/v1/products")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].productId").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].priceLabel").exists());
	      
	}
	@Test
	public void getProductDataWithShowWasNowAPI() throws Exception
	{
		MvcResult result = mvc.perform( MockMvcRequestBuilders
	      .get("/v1/products")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].productId").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].priceLabel").exists())
	      .andReturn();
		equals(result.getResponse().getContentAsString().matches(".*?Was £.*?, now .*?"));
	}
	
	@Test
	public void getProductDataWithShowPercDscountAPI() throws Exception
	{
		MvcResult result = mvc.perform( MockMvcRequestBuilders
	      .get("/v1/products").param("labelType", "ShowPercDscount")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].productId").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].priceLabel").exists())
	      .andReturn();
		equals(result.getResponse().getContentAsString().matches(".*?% off - now £.*?"));
	}
	
	public void getProductDataWithShowWasThenNowAPI() throws Exception
	{
		MvcResult result = mvc.perform( MockMvcRequestBuilders
	      .get("/v1/products").param("labelType", "ShowWasThenNow")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andDo(print())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].productId").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].priceLabel").exists())
	      .andReturn();
		equals(result.getResponse().getContentAsString().matches(".*?Was £.[0-9]*?, then £.[0-9]*?, now £[0-9]*?.*?"));
	}
	
}
