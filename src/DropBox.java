import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.io.FileNotFoundException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;



/**
 * This class is used to for connecting to the DROPBOX API
 *
 * Using an access token generated we can access out dropbox account and upload files
 *
 *
 * @author Ahad Malik
 **/

public class DropBox {


    private static final String ACCESS_TOKEN = "eNvPEeivaxAAAAAAAAAAL0T7Mrjm0CGmtAZzJRHokkJ74VayNsiIRQabZINrTVI-";


    public void uploadFile(){

        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try {
            // Get current account info
            FullAccount account = client.users().getCurrentAccount();
            System.out.println(account.getName().getDisplayName());

            // Get files and folder metadata from Dropbox root directory
            ListFolderResult result = client.files().listFolder("");
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    System.out.println(metadata.getPathLower());
                }

                if (!result.getHasMore()) {
                    break;
                }

                result = client.files().listFolderContinue(result.getCursor());
            }

            // Upload "test.txt" to Dropbox
            try (InputStream in = new FileInputStream("test.txt")) {
                FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                        .uploadAndFinish(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (DbxException e) {
        }
    }


}






















