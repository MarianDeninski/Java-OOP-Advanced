package hell.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CommandHandler {

    String executeCommand(String command, List<String> params) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
