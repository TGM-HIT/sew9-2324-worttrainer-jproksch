package jproksch;

import java.io.IOException;

public interface SaveType {
    void save(String path, Rechtschreibtrainer rechtschreibtrainer) throws IOException;
    void getInformation(Rechtschreibtrainer rechtschreibtrainer,String path);
    void reset(String path, Rechtschreibtrainer rechtschreibtrainer)throws IOException;
}