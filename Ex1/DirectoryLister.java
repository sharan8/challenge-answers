import java.io.File;
import java.util.Arrays;

/**
 * Class that takes in a directory filepath as an input and prints its contents.
 * @author Sharan Thangavel
 */
public class DirectoryLister {
	public static void main(String[] args) {
		// Handle case where no argument is given
		if (args.length == 0) {
			System.err.println("Error: Please provide a directory filepath as input");
			return;
		}

		// Retrieve the directory name input and create File object
		String dirName = args[0];
		File directory = new File(dirName);

		// Validate the input
		if (!isValidInput(directory)) {
			return;
		}

		// Retrieve a sorted array of contents
		File[] sortedContents = getDirectoryContents(directory);

		// Output list of contents to the user
		listDirectoryContentsToUser(sortedContents);
	}

	/**
	 * Checks if given input is valid.
	 *
	 * @param file created using the input filepath provided to main.
	 * @return true if valid directory and false otherwise.
	 */
	public static boolean isValidInput(File file) {
		// Handle case where the given directory name is invalid
		if (!file.exists()) {
			System.err.println("Error: Directory given doesn't exist");
			return false;
		}

		// Handle case where file name is given instead of directory name
		if (file.isFile()) {
			System.err.println("Error: File given but directory is expected");
			return false;
		}

		return true;
	}

	/**
	 * Outputs the contents of a directory to the user.
	 *
	 * @param directoryContents for the directory provided by user and validated.
	 */
	public static void listDirectoryContentsToUser(File[] directoryContents) {
		// Print details of each content object
		for (File content: directoryContents) {
			if (content.isFile()) {
				System.out.format("File Path:%s, ", content.getPath());
				System.out.format("File Name:%s, ", content.getName());
				System.out.format("File Size:%d bytes\n", content.length());
			} else if (content.isDirectory()) {
				System.out.format("Directory Path:%s, ", content.getPath());
				System.out.format("Directory Name:%s, ", content.getName());
				System.out.format("Directory Size:%d bytes\n", content.length());
			}
		}
	}

	/**
	 * Retrieves the contents of a directory sorted in order of file size.
	 *
	 * @param directory validated File object.
	 * @return an array of File objects sorted by increasing file size.
	 */
	public static File[] getDirectoryContents(File directory) {
		// Access the contents of the directory
		File[] directoryContents = directory.listFiles();

		// Sort the contents by their file size
		Arrays.sort(directoryContents, (a, b) -> Long.valueOf(a.length()).compareTo(Long.valueOf(b.length())));

		return directoryContents;
	}
}