package servlets_product_task.Controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets_product_task.dao.ProductDao;
import servlets_product_task.dto.Product;

public class ProductController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String brand = req.getParameter("brand");
		int price = Integer.parseInt(req.getParameter("price"));
		String manufacturer = req.getParameter("manufacturer");
		String state = req.getParameter("state");

		Product product = new Product();
		product.setName(name);
		product.setBrand(brand);
		product.setState(state);
		product.setManufacturer(manufacturer);//
		if (state.equals("KAR")) {
			ServletContext context = getServletContext();
			double CGST = Double.parseDouble(context.getInitParameter("CGST"));

			ServletConfig config = getServletConfig();
			double SGST = Double.parseDouble(config.getInitParameter("KAR"));

			double total_price = price + (CGST + SGST) * price;
			product.setPrice(total_price);

		} else {
			ServletContext context = getServletContext();
			double CGST = Double.parseDouble(context.getInitParameter("CGST"));

			ServletConfig config = getServletConfig();
			double SGST = Double.parseDouble(config.getInitParameter("TN"));

			double total_price = price + (CGST + SGST) * price;
			product.setPrice(total_price);
		}
		
		ProductDao dao= new ProductDao();
		dao.saveProduct(product);
		System.out.println("Saved successfully");

	}
}
