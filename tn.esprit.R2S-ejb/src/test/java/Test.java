import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by evilkid on 10/31/2016.
 */
public class Test {
    public static void main(String[] args) {
        String val = "this is a {{sample}} string with {{some}} special words. {{another one}}";
        String regex = "\\{\\{(.*?)\\}\\}";
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(val);
        while (matcher.find()) {
            list.add(matcher.group());
        }

        System.out.println(list);
    }
}
