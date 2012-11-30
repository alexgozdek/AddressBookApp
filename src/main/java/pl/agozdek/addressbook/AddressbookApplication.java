package pl.agozdek.addressbook;

import pl.agozdek.addressbook.domain.Person;
import pl.agozdek.addressbook.persistence.DBService;

import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;


public class AddressbookApplication extends Application {
	
	private static final long serialVersionUID = -3626521926573117015L;
	
	private BeanItemContainer<Person> peopleBeanContainer = new BeanItemContainer<Person>(Person.class);
	private Table peopleDataTable = new Table("", peopleBeanContainer);	
	private Window mainWindow = new Window();
	private Button addPersonButton = new Button("+");	
	private Window personFormWindow;
	
	private DBService dbService;	

	public AddressbookApplication(DBService dbService) {
		this.dbService = dbService;
	}
	
	public DBService getDBService() {
		return dbService;
	}
	
	@Override
	public void init() {
		
		dbService = new DBService();
		Application myApp = new AddressbookApplication(dbService);		
		initSampleData();
		updatePeopleTable();

		mainWindow.addComponent(peopleDataTable);

		peopleDataTable.setSelectable(true);
		peopleDataTable.setImmediate(true);
		
		peopleDataTable.addListener(new Table.ValueChangeListener() {

			private static final long serialVersionUID = 1L;
			
			public void valueChange(ValueChangeEvent event) {
				if (peopleDataTable.getValue() != null) {
					mainWindow.showNotification("" + peopleDataTable.getValue());
				}	
			}
		});
		
		addPersonButton.addStyleName("addButton");
		addPersonButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				
				if (personFormWindow != null) {
					mainWindow.removeWindow(personFormWindow);
				}
				
				// create new Person
				BeanItem<Person> beanPersonItem = new BeanItem<Person>(new Person());
				AddresBook addresBook  = new AddresBook(beanPersonItem, AddressbookApplication.this); 
				
				// create pop-up form
				personFormWindow = addresBook.createPopupWindow();
				mainWindow.addWindow(personFormWindow);
			}
		});

		mainWindow.addComponent(addPersonButton);
		setMainWindow(mainWindow);
		setTheme("addressbook-maventheme");		
	}
	
	public void updatePeopleTable(){
		peopleBeanContainer.removeAllItems();
		peopleBeanContainer.addAll(dbService.getAllPeople());
	}	
	
	private void initSampleData(){		
		Person p1 = new Person();
		p1.setFirstName("Anna");
		p1.setLastName("Kowalska");
		p1.setEmail("anna.kowalska@gmail.com");
		p1.setMessage("Vaadin is an open source Web application framework for rich Internet applications. ");
		dbService.addPerson(p1);

		Person p2 = new Person();
		p2.setFirstName("Jan");
		p2.setLastName("Nowak");
		p2.setEmail("jan.nowak@op.pl");
		p2.setMessage("On client-side Vaadin is built on top of and can be extended with Google Web Toolkit.");				
		dbService.addPerson(p2);
		
		Person p3 = new Person();
		p3.setFirstName("Ewa");
		p3.setLastName("Nowak");
		p3.setEmail("ewa.nowak@gmail.com");
		p3.setMessage("The framework incorporates event-driven programming and widgets.");				
		dbService.addPerson(p3);
		
		Person p4 = new Person();
		p4.setFirstName("Aleksander");
		p4.setLastName("Gozdek");
		p4.setEmail("gozdek.aleksamnder@gmail.com");
		p4.setMessage("HOW ABOUT 3 ??? :)");				
		dbService.addPerson(p4);
	}
}
