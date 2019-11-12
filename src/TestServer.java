import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.channels.*;
import java.util.*;
import java.awt.Desktop;
import javax.swing.JOptionPane;

/**
 * This class is used to interact with the test server.
 * You have to be on the AU network to use the test server and the first time you will be asked
 * for a test code which you can find on the Blackboard page, where you receive feedback on you projects.
 * Access the AU network from home: https://medarbejdere.au.dk/administration/it/vpn-remoteaudk/
 *
 * Please report errors and problems to this mail address: nis@cs.au.dk
 * Describe your problem as precisely as possible.
 *
 * @author  Nikolaj I. Schwartzbach.
 * @version 31-10-2019
 */
public class TestServer {

    private TestServer() {}

    public static void main(String[] args) {
        try {
            test("CG1");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Downloads the latest files from the course webpage.
     * @param exercise  one of: CG1, CG3, CG5
     */
    public static void download(String exercise) throws IOException {
        String ex = exercise.toLowerCase();

        switch(ex) {

            case "cg1":
                downloadCourseFiles("cg1","Game.java","GUI.java",
                    "Settings.java","Player.java","RandomPlayer.java",
                    "GreedyPlayer.java","SmartPlayer.java","CGTest.java",
                    "network.dat","map.png");
                break;
            case "cg3":
                downloadCourseFiles("cg3","Game.java","GUI.java","Generator.java",
                    "Player.java","RandomPlayer.java",
                    "GreedyPlayer.java","SmartPlayer.java");
                break;
            case "cg5":
                downloadCourseFiles("cg5","LogPlayer.java","Game.java","Settings.java");
                break;
        }
    }
    
    /**
     * Tests exercise and open the resulting link in the default browser (if uploaded successfully).
     * @param exercise   Short name of exercise.
     */
    public static void testAndOpenInBrowser(String exercise) throws IOException, URISyntaxException {
        String str = test(exercise);
        if(str.contains("Link: ")){
            str = str.substring(str.indexOf("Link: ")+6);
            Desktop.getDesktop().browse(new URL(str).toURI());
        }
    }

    /**
     * Tests exercise in the classpath (the folder from which the program is invoked).
     * @param exercise   Short name of exercise.
     */
    public static String test(String exercise) throws IOException {
        String ex = exercise.toLowerCase();
        // Files to be uploaded
        String[] files = null;
        switch(ex) {

            // DieCup
            case "dc1":
            case "dc2":
            case "dc3-1":
            case "dc3-4":
            files = new String[] {"Die","DieCup"}; break;

            case "dt-2":
            files = new String[] {"Median","MedianTest"}; break;

            // 2016
            case "basketplayer":
            files = new String[] {"BasketPlayer","BasketTeam"}; break;
            case "cow":
            files = new String[] {"Cow","DiaryFarm"}; break;
            case "field-1":
            case "field-2":
            files = new String[] {"Field","Farm"}; break;
            case "goat":
            files = new String[] {"Goat","GoatFarm"}; break;
            case "handballplayer":
            files = new String[] {"HandballPlayer","HandballTeam"}; break;
            case "image":
            files = new String[] {"Image","UsbStick"}; break;
            case "soccerplayer":
            files = new String[] {"SoccerPlayer","SoccerTeam"}; break;
            case "volleyplayer":
            files = new String[] {"VolleyPlayer","VolleyTeam"}; break;
            case "passenger":
            files = new String[] {"Passenger","Ferry"}; break;
            case "picture":
            files = new String[] {"Picture","UsbStick"}; break;
            case "song":
            files = new String[] {"Song","DVD"}; break;

            // 2017
            case "animal":
            files = new String[] {"Animal","Zoo"}; break;
            case "brick-1":
            case "brick-2":
            files = new String[] {"Brick","LegoBox"}; break;
            case "carpet-1":
            case "carpet-2":
            files = new String[] {"Carpet","Shop"}; break;
            case "tool-1":
            case "tool-2":
            files = new String[] {"Tool","ToolBox"}; break;
            case "vegetable-1":
            case "vegetable-2":
            files = new String[] {"Vegetable","Shop"}; break;
            case "nail":
            files = new String[] {"Nail","Box"}; break;
            case "screw":
            files = new String[] {"Screw","Box"}; break;
            case "chicken":
            files = new String[] {"Chicken","ChickenYard"}; break;
            case "pigeon":
            files = new String[] {"Pigeon","PigeonLoft"}; break;
            case "penguin":
            files = new String[] {"Penguin","Group"}; break;

            // 2018
            case "ferry":
            files = new String[] {"Ferry","Harbour"}; break;
            case "train-1":
            case "train-2":
            files = new String[] {"Train","TrainStation"}; break;
            case "bus-1":
            case "bus-2":
            files = new String[] {"Bus","BusStation"}; break;
            case "flight":
            files = new String[] {"Flight","Airport"}; break;

            // 2019
            case "chapter":
            files = new String[]{ "Chapter", "Book" }; break;
            case "cheese":
            files = new String[]{ "Cheese", "Cooler" }; break;
            case "drink":
            files = new String[]{ "Drink", "Refrigerator" }; break;
            case "flower":
            files = new String[]{ "Flower", "Bouqet" }; break;
            case "food":
            files = new String[]{ "Food", "DeepFreezer" }; break;
            case "fruit":
            files = new String[]{ "Fruit", "Basket" }; break;
            case "pearl":
            files = new String[]{ "Pearl", "Necklace" }; break;
            case "photo":
            files = new String[]{ "Photo", "Album" }; break;

            // Videos
            case "phone":
            files = new String[] {"Phone","WebShop"}; break;
            case "pirate":
            files = new String[] {"Pirate","PirateShip"}; break;
            case "car":
            files = new String[] {"Car","PirateShip"}; break;
            case "turtle":
            files = new String[] {"Turtle","Zoo"}; break;

            // Handins
            case "musician":
            files = new String[] {"Musician", "Band"}; break;
            case "racer":
            files = new String[] {"Racer", "FormulaOne"}; break;
            case "dog":
            files = new String[] {"Dog", "Kennel"}; break;
            case "boat":
            files = new String[] {"Boat", "Marina"}; break;
            case "biker":
            files = new String[] {"Biker", "MotorcycleClub"}; break;
            case "film":
            files = new String[] {"Film", "FilmCollection"}; break;

            case "cg1-1":
            files = new String[] {"City"}; break;
            case "cg1-2":
            files = new String[] {"Road"}; break;
            case "cg1-3":
            files = new String[] {"Position"}; break;
            case "cg1-4":
            files = new String[] {"Country"}; break;
            case "cg1-5":
            files = new String[] {"City","Country"}; break;
            case "cg1-6":
            files = new String[] {"Country"}; break;
            case "cg1-7":
            files = new String[] {"Country"}; break;
            case "cg1":
            files = new String[] {"City","Road","Position","Country"}; break;

            case "cg2-1":
            files = new String[] {"RoadTest"}; break;
            case "cg2-2":
            files = new String[] {"PositionTest"}; break;
            case "cg2-3":
            files = new String[] {"CityTest"}; break;
            case "cg2-4":
            files = new String[] {"CountryTest"}; break;
            case "cg2":
            files = new String[] {"RoadTest","PositionTest","CityTest","CountryTest"}; break;

            case "cg3-1":
            files = new String[] {"BorderCity","BorderCityTest"}; break;
            case "cg3-2":
            files = new String[] {"CapitalCity","CapitalCityTest"}; break;
            case "cg3-3":
            files = new String[] {"MafiaCountry","MafiaCountryTest"}; break;
            case "cg3-4":
            files = new String[] {"City","CityTest"}; break;
            case "cg3":
            files = new String[] {"City","BorderCity","CapitalCity","MafiaCountry"}; break;

            case "cg5":
            files = new String[] {"Log"}; break;

            default:
            System.err.println("Invalid exercise: "+ exercise +"."); return "Invalid exercise: "+ exercise +".";
        }

        Map<String,String> arguments = new HashMap<>();

        // Check upload code
        String auID="", code = "";

        // Check if auID/code are saved
        Path dataPath = Paths.get("upload-data.dat");
        if(Files.exists(dataPath)){
            // Read first line
            String line = Files.readAllLines(dataPath).get(0);

            // Split into auID and code
            String[] data = line.split(" ");

            auID = data[0];
            code = data[1];
        }

        // Check if information is missing
        if(auID.isEmpty() || code.isEmpty()){
            // Get user input
            Scanner s = new Scanner(System.in);

            System.out.print("Det ser ud til, du endnu ikke har uploadet til testserveren.\n" +
                "For at forts?te, skal vi bruge dit auID og uploade kode (findes p?Blackboard under My Grades).\n" +
                "Indtast auID: ");

            auID = s.nextLine();
            System.out.print("Indtast upload kode: ");
            code = s.nextLine();

            // Save information for next time
            PrintWriter pw = new PrintWriter("upload-data.dat");
            pw.println(auID + " " + code);
            pw.close();
        }

        // Set meta information
        arguments.put("h",ex.replace("dc3-1","dc3a").replace("dc3-4","dc3b"));
        arguments.put("auID",auID);
        arguments.put("code",code);

        // Check GDPR
        Map<String, String> args = new HashMap<>();
        args.put("auID",auID);
        String res = sendPost("http://dintprog-server.cs.au.dk/gdpr.php", args);
        if(!res.startsWith("GDPR success")) {
            System.out.print("\nAf hensyn til overholdelse af GDPR, har vi behov for dit samtykke til behandling af dine personoplysninger.\n"+
                "Nikolaj I. Schwartzbach er dataansvarlig og kan kontaktes pr. email p?nis@cs.au.dk.\n" +
                "Vi behandler dine personoplysninger til flg. form?:"+
                "\n - Administrering af afleveringer til kurset Introduktion til Programmering.\n" +
                "\nVi behandler flg. personoplysninger om dig:\n" +
                " - Dit fulde navn\n"+
                " - Dit auID og studienr.\n"+
                " - Metadata om alle uploads (herunder tidspunkt/useragent mm.)\n"+
                "\nDine personoplysninger videregives ikke tredjeparter og behandles kun i EU/E?.\n"+
                "\nDine personoplysninger gemmes p?ubestemt tid, men du kan til enhver tid f?din information slettet/rettet.\n"+
                "\nSamtykker du til dette (skriv 'Ja')?\n:> ");
            Scanner s = new Scanner(System.in);
            String answer = s.nextLine().toLowerCase();

            // Check answer
            if(answer.equals("ja")){
                // Log consent
                try{
                    args = new HashMap<>();
                    args.put("auID",auID);
                    args.put("code",code);
                    res = sendPost("http://dintprog-server.cs.au.dk/gdpr.php", args);
                    if(!res.startsWith("GDPR success")){
                        if(res.startsWith("Invalid")) {
                            try{
                                System.out.println("\nForkert auID eller upload kode.");
                                Files.delete(dataPath);
                            }
                            catch(IOException e) {
                                e.printStackTrace();
                            }
                        } else System.out.println("\n"+res);
                        return "GDPR fail";
                    }
                } catch(IOException e){ e.printStackTrace(); return "IOException: "+e.getMessage(); }
            } else return "Not consent";
            System.out.println();
        }

        // Check all files and accumulate contents
        for(String file : files) {
            Path p = Paths.get("src/"+file+".java");
            // Check if file exists
            if(Files.exists(p)) {
                String str = new String(Files.readAllBytes(p), Charset.defaultCharset());
                arguments.put(file.toLowerCase(), str);
            }
            else {
                System.err.println("No file with this name '" + file + ".java' in folder " + System.getProperty("user.dir")  +".");
                return "No file with this name '" + file + ".java' in folder " + System.getProperty("user.dir")  +".";
            }
        }

        // Send request and handle response
        String response = sendPost("http://dintprog-server.cs.au.dk/upload.php",arguments);
        System.out.println(response);
        if(response.startsWith("Invalid")) {
            try{
                Files.delete(dataPath);
                test(ex);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * This method downloads a variable number of files as specified by files.
     * @param version   The version of the files to download. One of:  cg1, cg2, cg3.
     * @param files     The files to download.
     */
    private static void downloadCourseFiles(String version, String... files)  {
        for(String file : files)
            try {
                downloadFile("https://tildeweb.au.dk/au531793/dintprog/e19/opgaver/"+version+"/"+file,file);
            } catch(IOException e){
                System.err.println("Unable to download file: "+file+". Reason: "+e.getMessage());
            }
            
        JOptionPane.showMessageDialog(null, "Download af filer for version '"+version+"' blev gennemført succesfuldt. Det kan være nødvendigt at genstarte BlueJ for at se de nye filer.", "Filer downloadet succesfuldt.", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method downloads a file from a url and saves it to the local machine.
     * @param url The url from which to download.
     * @param dest The destination of the downloaded file.
     */
    private static void downloadFile(String url, String dest) throws IOException {
        if(Files.exists(Paths.get(dest)))
            Files.delete(Paths.get(dest));
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(dest);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    /**
     * This method sends an HTTP POST request to a specified location with some arguments.
     * @param location     URL to POST to.
     * @param arguments    HTTP header arguments.
     * @return   Response from the URL (if any).
     * @throws   IOException   For various reasons.
     */
    private static String sendPost(String location, Map<String,String> arguments) throws IOException {

        URL url = new URL(location);
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        con.setRequestProperty("User-Agent", "Java client");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);

        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        StringBuilder sb;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()))) {
            String line;
            sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }
        http.disconnect();
        return sb.toString();
    }
}