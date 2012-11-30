package pl.agozdek.addressbook;

import pl.agozdek.addressbook.domain.Person;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;


public class AddresBook extends Form implements FormFieldFactory {

	private static final long serialVersionUID = -5598713425194481770L;
	
	private AbsoluteLayout mainLayout;
	private ListSelect listSelect_1;
	private GridLayout gridLayout_1;
	
	Window popupWindow;
	
	private Button addButton = new Button("Add");
	private Button closeButton = new Button("Close");
	
	private TextField textField_4;
	private Label label_4;
	private TextField textField_3;
	private Label label_3;
	private TextField textField_2;
	private Label label_2;
	private TextField textField_1;
	private Label label_1;
		
	private AddressbookApplication app;
	BeanItem<Person> personBeanItem;	

	public AddresBook() {
		buildMainLayout();
		//setCompositionRoot(mainLayout);

		// TODO add user code here
	}
	
	public AddresBook(BeanItem<Person> personBeanItm, AddressbookApplication application){
		
		this.app = application;
		GridLayout layout = new GridLayout(2, 5);
		layout.setSpacing(true);
		setLayout(layout);
		
		setImmediate(true);
		setValidationVisibleOnCommit(true);
		setFormFieldFactory(this);
		
		this.personBeanItem = personBeanItm;		
		setItemDataSource(personBeanItem);
		
		addButton.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				commit();
				Person person = personBeanItem.getBean();
				app.getDBService().addPerson(person);
				closePopupWindow();
				app.updatePeopleTable();
			}
		});		
		
		closeButton.addListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 5744203146867593506L;

			public void buttonClick(ClickEvent event) {
				closePopupWindow();
				app.updatePeopleTable();
			}
		});

		layout.addComponent(addButton);
		layout.addComponent(closeButton);		
	}
	
	@Override
	protected void attachField(Object propertyId, Field field) {
		
		String property = (String) propertyId;
		GridLayout gl = (GridLayout) getLayout();

		if ("firstName".equals(property)) {
			gl.addComponent(field, 0, 0, 1, 0);
		} else if ("lastName".equals(property)) {
			gl.addComponent(field, 0, 1, 1, 1);
		} else if ("email".equals(property)) {
			gl.addComponent(field, 0, 2, 1, 2);
		} else if ("message".equals(property)) {
			gl.addComponent(field, 0, 3, 1, 3);
		}
	}

	public Field createField(Item item, Object propertyId, Component uiContext) {

		String field = (String) propertyId;

		if ("firstName".equals(field)) {
			TextField firstNameTextField = new TextField("Imi�");

			return firstNameTextField;
		} else if ("lastName".equals(field)) {
			TextField lastNameTextField = new TextField("Nazwisko");
			lastNameTextField.setRequired(true);
			
			return lastNameTextField;
		} else if ("email".equals(field)) {
			TextField emailTextField = new TextField("Email");
			emailTextField.setRequired(false);
			
			return emailTextField;
		} else if ("message".equals(field)) {
			TextField messageTextField = new TextField("Wiadomo��");
			messageTextField.setRequired(true);
			
			return messageTextField;
		}
		return null;
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		mainLayout.addComponent(gridLayout_1);
		
		// listSelect_1
		listSelect_1 = new ListSelect();
		listSelect_1.setImmediate(false);
		listSelect_1.setWidth("200px");
		listSelect_1.setHeight("-1px");
		mainLayout.addComponent(listSelect_1, "top:130.0px;left:170.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("320px");
		gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(false);
		gridLayout_1.setColumns(2);
		gridLayout_1.setRows(5);
		
		// label_3
		label_3 = new Label();
		label_3.setCaption("Imi�");
		label_3.setImmediate(false);
		label_3.setWidth("-1px");
		label_3.setHeight("-1px");
		label_3.setValue("Label");
		gridLayout_1.addComponent(label_3, 0, 0);
		
		// textField_1
		textField_1 = new TextField();
		textField_1.setImmediate(false);
		textField_1.setWidth("-1px");
		textField_1.setHeight("-1px");
		textField_1.setSecret(false);
		gridLayout_1.addComponent(textField_1, 1, 0);
		
		// label_2
		label_2 = new Label();
		label_2.setCaption("Nazwisko");
		label_2.setImmediate(false);
		label_2.setWidth("-1px");
		label_2.setHeight("-1px");
		label_2.setValue("Label");
		gridLayout_1.addComponent(label_2, 0, 1);
		
		// textField_2
		textField_2 = new TextField();
		textField_2.setImmediate(false);
		textField_2.setWidth("-1px");
		textField_2.setHeight("-1px");
		textField_2.setSecret(false);
		gridLayout_1.addComponent(textField_2, 1, 1);
		
		// label_1
		label_1 = new Label();
		label_1.setCaption("Wiadomo��");
		label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Label");
		gridLayout_1.addComponent(label_1, 0, 2);
		
		// textField_3
		textField_3 = new TextField();
		textField_3.setImmediate(false);
		textField_3.setWidth("-1px");
		textField_3.setHeight("-1px");
		textField_3.setSecret(false);
		gridLayout_1.addComponent(textField_3, 1, 2);
		
		// label_4
		label_4 = new Label();
		label_4.setCaption("E-mail");
		label_4.setImmediate(false);
		label_4.setWidth("-1px");
		label_4.setHeight("-1px");
		label_4.setValue("Label");
		gridLayout_1.addComponent(label_4, 0, 3);
		
		// textField_4
		textField_4 = new TextField();
		textField_4.setImmediate(false);
		textField_4.setWidth("-1px");
		textField_4.setHeight("-1px");
		textField_4.setSecret(false);
		gridLayout_1.addComponent(textField_4, 1, 3);
		
		// button_1
		addButton = new Button();
		addButton.setCaption("Button");
		addButton.setImmediate(false);
		addButton.setWidth("-1px");
		addButton.setHeight("-1px");
		
		addButton.addListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				commit();
				Person person = personBeanItem.getBean();
				app.getDBService().addPerson(person);
				closePopupWindow();
				app.updatePeopleTable();
			}
		});
		
		gridLayout_1.addComponent(addButton, 1, 4);
		
		closeButton = new Button();
		closeButton.setCaption("Button");
		closeButton.setImmediate(false);
		closeButton.setWidth("-1px");
		closeButton.setHeight("-1px");
		closeButton.addListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 5944303146867591506L;

			public void buttonClick(ClickEvent event) {
				closePopupWindow();
				app.updatePeopleTable();
			}
		});		
		gridLayout_1.addComponent(closeButton, 0, 4);
		
		return gridLayout_1;
	}
	
	public Window createPopupWindow() {		
		popupWindow = new Window();		
		popupWindow.setModal(true);		
		popupWindow.setClosable(false);
		popupWindow.addComponent(this);
		((VerticalLayout) popupWindow.getContent()).setSizeUndefined();
		((VerticalLayout) popupWindow.getContent()).setMargin(true);
		((VerticalLayout) popupWindow.getContent()).setSpacing(true);

		return popupWindow;
	}

	private void closePopupWindow() {
		Window mainWindow = popupWindow.getParent();
		mainWindow.removeWindow(popupWindow);
	}
}