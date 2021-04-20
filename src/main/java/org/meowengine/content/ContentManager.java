package org.meowengine.content;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

@Slf4j
public class ContentManager {

    private final List<String> AbsolutePathList;
    private final List<ClassLoader> SourceList;

    private final Pattern classpath;
    private final Pattern file;

    private Consumer<Object> error;

    public ContentManager() {
        AbsolutePathList = new ArrayList<String>();
        SourceList = new ArrayList<>();
        error = this::default_error_callback;

        classpath = Pattern.compile("classpath://");
        file = Pattern.compile("file://");
    }

    /**
     * Adds source where to find resource
     * @param loader
     */
    public void addSource(ClassLoader loader) {
        SourceList.add(loader);
    }

    public void addAbsolutePath(String path) {
        log.debug("New resource directory: " + path);
        log.debug("exists: " + new File(path).exists());
        log.debug("isDirectory " + new File(path).isDirectory());
        if (new File(path).exists() && new File(path).isDirectory()) {
            AbsolutePathList.add(path + "/");
        } else {
            log.error(path + " added to ContentManager source, but it does not exists or not a directory. Skipping it");
        }
    }
    public boolean removeAbsolutePath(String path) {
        return AbsolutePathList.remove(path);
    }

    /**
     * Removes that source from search list
     * @param loader
     */
    public boolean removeSource(ClassLoader loader) { return SourceList.remove(loader); }

    public Optional<InputStream> getResourceAsStream(String location) {
        if (location.startsWith(file.pattern())) {
            String loc = file.matcher(location).replaceFirst("");
            Optional<String> loader = AbsolutePathList.stream().filter(
                    s -> new File(s + loc).exists()
            ).findFirst();

            if (loader.isPresent()) {
                String s = loader.get();
                InputStream stream = null;
                try {
                    stream = new FileInputStream(s + loc);
                } catch (FileNotFoundException ex) {
                    log.error("File {} not found", s + loc);
                }
                return Optional.ofNullable(stream);
            }
            return Optional.empty();
        }
        if (location.startsWith(classpath.pattern())) {
            String loc = classpath.matcher(location).replaceFirst("");
            Optional<ClassLoader> loader = SourceList.stream().filter(
                    classLoader -> classLoader.getResourceAsStream(loc) != null
            ).findFirst();

            return loader.map(classLoader -> classLoader.getResourceAsStream(loc));
        }

        log.warn("File {} is not presented in path", location);
        return Optional.empty();
    }

    public Optional<URL> getResource(String location) {
        if (location.startsWith(classpath.pattern())) {
            String loc = classpath.matcher(location).replaceFirst("");
            Optional<ClassLoader> loader = SourceList.stream().filter(
                    classLoader -> classLoader.getResourceAsStream(loc) != null
            ).findFirst();

            return loader.map(classLoader -> classLoader.getResource(loc));
        }
        if (location.startsWith(file.pattern())) {
            String loc = file.matcher(location).replaceFirst("");
            Optional<String> loader = AbsolutePathList.stream().filter(
                    s -> new File(s + loc).exists()
            ).findFirst();

            if (loader.isPresent()) {
                try {
                    URL url = new File(loader.get() + loc).toURI().toURL();
                    return Optional.of(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            return Optional.empty();
        }
        log.warn("File {} is not presented in path", location);
        return Optional.empty();
    }

    public final JsonElement getResourceDeclaration(String location) {
        var optional = getResourceAsStream(location);
        if (optional.isEmpty()) {
            throw new RuntimeException("Failed to load resource declaration: " + location);
        }
        JsonElement json;
        try {
            json = JsonParser.parseReader(new InputStreamReader(optional.get()));
        } catch (JsonSyntaxException exception) {
            throw new RuntimeException("JsonSyntaxException thrown when load resource: " + location);
        }
        return json;
    }

    public final void setErrorCallback(Consumer<Object> callback) {
        this.error = callback;
    }

    protected final void invokeCallback(Object param) {
        log.error("Content manager invoked error callback");
        error.accept(param);
    }

    private void default_error_callback(Object error) {
        throw new RuntimeException((String) error);
    }



}
