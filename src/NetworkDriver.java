import java.io.*;
import java.util.ArrayList;

/**
 * /**
 * Suraj Sharma
 * Id # 109606910
 * .
 */
public class NetworkDriver {

    public static void main(String Args[]) {

        String csvFile = "socfb-Amherst41.mtx";
        //String csvFile = "TestData.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = " ";

        try {

            Network n = new Network();

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                String[] netData = line.split(splitBy);
                n.addLineToNetwork(netData);

            }
            //n.netWorkOverlap();
           // n.printPeople();
           //System.out.println(n.netWorkOverlap().size());
           //n.clusteringCoefficient();
            n.friendshipParadox();

            BufferedWriter bw = null;
            try {

                File file = new File("output.csv");
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                int [][] writer =n.histogramList();

                for (int i=0;i<writer[0].length;i++)
                    bw.write(writer[0][i]+","+writer[1][i]+"\n");
                //System.out.println("File written Successfully");
                bw.close();
            }catch (IOException ioe) {
                ioe.printStackTrace();
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}