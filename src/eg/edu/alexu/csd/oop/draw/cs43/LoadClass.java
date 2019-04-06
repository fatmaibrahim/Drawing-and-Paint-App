package eg.edu.alexu.csd.oop.draw.cs43;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LoadClass {
	
	
	
	private JarFile jarFile;

	public Class<?> excute(String path ,String nameclass) throws IOException, ClassNotFoundException{
	jarFile = new JarFile(path);
	Enumeration<JarEntry> e = jarFile.entries();

	URL[] urls = { new URL("jar:file:" + path+"!/") };
	URLClassLoader cl = URLClassLoader.newInstance(urls);

	while (e.hasMoreElements()) {    //e.hasMoreElements()
	    JarEntry je = e.nextElement();
	    if(je.isDirectory() || !je.getName().endsWith(".class")||!je.getName().contains(nameclass)){
		   continue;
	    }
	    // -6 because of .class
	    String className = je.getName().substring(0,je.getName().length()-6);
	   System.out.println(className);
	    className = className.replace('/', '.');
	    Class<?> c = cl.loadClass(className);
        
	    return c;
	}
	return null;
	}
}