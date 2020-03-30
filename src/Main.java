import model.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String pwd = System.getProperty("user.dir");
        File detectionDir = new File(pwd + "/sample/detection");
        File imageDir = new File(pwd + "/sample/image");

        List<String > labelList = new ArrayList<>();
        labelList.add("Idunno");
        labelList.add("Notseen");
        labelList.add("Seen");

        System.out.println(InspectionFactory.createInspection(
                detectionDir,imageDir,labelList
        ));
    }
}
