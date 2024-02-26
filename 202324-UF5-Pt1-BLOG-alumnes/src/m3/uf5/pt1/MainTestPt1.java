package m3.uf5.pt1;

import java.util.Date;

public class MainTestPt1 {
	public static final String BLOG_FILENAME = "dades-blog.xml";

	public static void main(String[] args) throws Exception {
		Blog blog = new Blog();

		blog.nouUsuari("pere@marianao.lan", "pere");
		blog.nouUsuari("maria@marianao.lan", "maria");
		blog.nouUsuari("joan@marianao.lan", "joan");
		blog.nouUsuari("julia@marianao.lan", "julia");


		// http://www.blindtextgenerator.com
		blog.afegirEntrada("pere@marianao.lan", "Lorem ipsum I",
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
				+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, "
				+ "nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. ");
		blog.afegirEntrada("pere@marianao.lan", "Lorem ipsum II",
				"Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. "
				+ "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. "
				+ "Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi.");
		blog.afegirEntrada("pere@marianao.lan", "Lorem ipsum infinitum",
				"Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, "
				+ "eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. "
				+ "Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. "
				+ "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. "
				+ "Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, "
				+ "sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. "
				+ "Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. "
				+ "Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. "
				+ "Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, "
				+ "leo eget bibendum sodales, augue velit cursus nunc, ");
		blog.afegirEntrada("pere@marianao.lan", "I Cicero",
  				"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, "
				+ "totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. "
				+ "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, "
				+ "sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt");
		blog.afegirEntrada("pere@marianao.lan", "II Cicero",
				"Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, "
				+ "adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. "
				+ "Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? "
				+ "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, "
				+ "vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? ");

		blog.comentarEntrada("pere@marianao.lan", new Date(), "I Cicero",
				"Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit,... ", 3);
		blog.comentarEntrada("pere@marianao.lan", new Date(), "I Cicero",
				"...sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.", 3);
		blog.comentarEntrada("pere@marianao.lan", new Date(), "II Cicero", "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, "
				+ "adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.", 2);
		blog.comentarEntrada("maria@marianao.lan", new Date(), "I Cicero", "Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit ", 2);
		blog.comentarEntrada("maria@marianao.lan", new Date(), "I Cicero", "laboriosam, nisi ut aliquid ex ea commodi consequatur?", 2);

		//blog.carregarDadesBlog(BLOG_FILENAME);
		System.out.println(blog.imprimirBlog());

		//System.out.println(blog.imprimirEntrada(new Date(), "Lorem ipsum infinitum"));
		//System.out.println(blog.imprimirEntrada(new Date(), "I Cicero"));

		blog.desarDadesBlog(BLOG_FILENAME);
	}
}
