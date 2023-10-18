package jproksch;

import java.io.IOException;

public interface SaveType {
    void save(String path, Rechtschreibtrainer worttrainer) throws IOException;
    void getInformation(Rechtschreibtrainer worttrainer,String path);
    void reset(String path, Rechtschreibtrainer worttrainer)throws IOException;
}