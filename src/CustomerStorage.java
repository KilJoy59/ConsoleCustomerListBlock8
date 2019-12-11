import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("[/.+@.+\\..+/i]");
    public static final  Pattern VALID_PHONE_REGEX =
            Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException(" Неверный формат ввода команды. Правильный формат: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[0] + " " + components[1];
        if (!emailValidate(components[2])) {
            throw new IllegalMailFormatException("Неправильный формат ввода почты");
        }
        if (!phoneValidate(components[3])) {
            throw new IllegalPhoneFormatException("Неправильный формат воода телефона");
        }

        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }

    public static boolean emailValidate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean phoneValidate(String phoneStr) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(phoneStr);
        return matcher.find();
    }




}