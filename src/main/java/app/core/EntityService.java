package app.core;

import java.io.IOException;
import java.util.List;

public interface EntityService<T> {

    ResponseDetails<T> getByName(String name) throws Exception;

    ResponseDetails<List<T>> getAll(List<String> names) throws IOException;
}
