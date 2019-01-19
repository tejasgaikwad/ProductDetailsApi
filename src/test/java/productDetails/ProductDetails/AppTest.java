package productDetails.ProductDetails;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.productDetails.beans.Price;
import com.springboot.productDetails.beans.Products;
import com.springboot.productDetails.service.ProductDetailsServiceImpl;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 * 
	 * @throws Exception
	 */
	
	
	private ProductDetailsServiceImpl ps;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		ps = new ProductDetailsServiceImpl();
	}

	@Test
    public void testNowPrice() throws Exception
    {
		Products p = new Products();
		Price price = new Price();
		price.setCurrency("GDP");
		price.setNow("59.00");
		p.setPrice(price);
		ps.populateNowPrice(p);
		assertEquals("£59", p.getNowPrice());		
    }
	
	@Test
    public void testPriceLabel_With_ShowWasThenNow() throws Exception
    {
		Products p = new Products();
		Price price = new Price();
		price.setCurrency("GDP");
		price.setNow("59.00");
		price.setThen1("65.00");
		price.setWas("70.00");
		p.setPrice(price);
		ps.populatePriceLabel(p, "ShowWasThenNow");
		assertEquals("Was £70, then £65, now £59", p.getPriceLabel());		
    }
	
	@Test
    public void testPriceLabel_With_ShowPercDscount() throws Exception
    {
		Products p = new Products();
		Price price = new Price();
		price.setCurrency("GDP");
		price.setNow("59.00");
		price.setThen1("65.00");
		price.setWas("70.00");
		p.setPrice(price);
		ps.populatePriceLabel(p, "ShowPercDscount");
		assertEquals("16% off - now £59", p.getPriceLabel());		
    }
	
	@Test
    public void testPriceLabel_With_ShowWasNow() throws Exception
    {
		Products p = new Products();
		Price price = new Price();
		price.setCurrency("GDP");
		price.setNow("59.00");
		price.setThen1("65.00");
		price.setWas("70.00");
		p.setPrice(price);
		ps.populatePriceLabel(p, "ShowWasNow");
		assertEquals("Was £70, now £59", p.getPriceLabel());		
    }
}
