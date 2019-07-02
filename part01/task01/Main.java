package main.java.part01.lesson08.task01;

import com.helger.jcodemodel.*;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * read code from the console
 * write it into method doWork
 * Create SomeClass.java which implements Worker
 * call doWork
 * @author L
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Сode: ");
        String code = in.nextLine();
        in.close();

        createClass(code);

        Path path = Paths.get("src/main/java/part01/lesson08/task01/SomeClass.java");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        fileManager.setLocation(StandardLocation.SOURCE_OUTPUT,  Arrays.asList(path.toFile().getParentFile()));

//        String[] opt = new String[]{"-d", "src/target/production/innopolis"};
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(path.toFile()));
        compiler.getTask(null, fileManager, null,null, null, compilationUnits).call();
        fileManager.close();

        ClassLoader classLoader = new MyClassLoader();
        Class<?> someClass = classLoader.loadClass("main.java.part01.lesson08.task01.SomeClass");

        Method doWork = someClass.getDeclaredMethod("doWork");
        doWork.invoke(someClass.newInstance());
    }

    /**
     * use codeModel lib
     * create class SomeClass.class in output
     * method doWork contains code from the console
     * @param code
     */
    private static void createClass(String code){
        JCodeModel codeModel = new JCodeModel();

        JPackage jp = codeModel._package(Main.class.getPackage().getName());
        try {
            JDefinedClass jc = jp._class("SomeClass");
            jc._implements(Worker.class);
            JMethod getter = jc.method(JMod.PUBLIC, codeModel.VOID, "doWork");

            JBlock body = getter.body();

            body.directStatement(code);

        } catch (JClassAlreadyExistsException e) {
            e.printStackTrace();
        }
        try {
            codeModel.build(new File("src/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
