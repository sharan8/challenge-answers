import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that outputs the anagram word pairs in a given list of words.
 * @author Sharan Thangavel
 */
public class AnagramChecker {
	public static void main(String[] args) {
		String[] wordList = {"vase", "bat", "gods", "latte", "name", "apres", "spit", 
			"joke", "ham", "dog", "act", "tale", "parse", "pits", "asper", "tab", 
			"table", "mane", "late", "god", "cat", "table", "save", "spare"};

		// Compute the anagram pairs from the given word list
		HashMap<String, String> anagramPairs = getAnagramPairs(wordList);

		// Print the computed anagram pairs to the user
		printAnagramPairsToUser(anagramPairs);
	}

	/**
	 * Returns the anagram pairs in the given word list.
	 *
	 * @param wordList of input words.
	 * @return a hashmap of anagram pairs as key-values.
	 */
	public static HashMap<String, String> getAnagramPairs(String[] wordList) {
		// Map each charFreqMap to its corresponding words
		HashMap<HashMap<Character, Integer>, ArrayList<String>> freqMapToWords = calcCharFrequencyMap(wordList);
		// Create a hashmap of anagram pairs
		HashMap<String, String> anagramPairsMap = new HashMap<>();

		// For each charFreq map, create pairs of anagrams 
		for (HashMap<Character, Integer> key: freqMapToWords.keySet()) {
			// Retrieve the list of anagrams with this particular charFreqMap
			ArrayList<String> anagrams = freqMapToWords.get(key);

			// Skip the words with a unique char frequency map
			if (anagrams.size() < 2) {
				continue;
			}

			// Populate the anagram pairs map
			for (int i=0; i<anagrams.size()-1; i++) {
				for (int j=i+1; j<anagrams.size(); j++) {
					anagramPairsMap.put(anagrams.get(i), anagrams.get(j));
				}
			}
		}

		return anagramPairsMap;
	}

	/**
	 * Prints given anagram pairs to standard output.
	 *
	 * @param pairs of anagrams.
	 */
	public static void printAnagramPairsToUser(HashMap<String, String> pairs) {
		for (String key: pairs.keySet()) {
			System.out.println(key + " - " + pairs.get(key));
		}
	}

	/**
	 * Calculates a map of each unique char freqency combination mapped to its respective words (charFreqMap).
	 *
	 * @param wordList of input words.
	 * @return a hashmap of each unique char frequency combination mapped to a list of its corresponding words.
	 */
	public static HashMap<HashMap<Character, Integer>, ArrayList<String>> calcCharFrequencyMap(String[] wordList) {
		HashMap<HashMap<Character, Integer>, ArrayList<String>> freqMapToWords = new HashMap<>();

		for (String word: wordList) {
			// Calcular the map of character frequencies for this word
			HashMap<Character, Integer> charFreqMap = calcCharFrequencyForWord(word);

			// Check if the particular charFreqMap is already present
			if (freqMapToWords.containsKey(charFreqMap)) {
				freqMapToWords.get(charFreqMap).add(word);
			} else {
				ArrayList<String> anagrams = new ArrayList<String>();
				anagrams.add(word);
				freqMapToWords.put(charFreqMap, anagrams);
			}
		}

		return freqMapToWords;
	}

	/**
	 * Calculates a map of unique characters present in a word and the number of times they occur.
	 *
	 * @param word to be calculated.
	 * @return a hashmap of characters and their corresponding frequencies.
	 */
	public static HashMap<Character, Integer> calcCharFrequencyForWord(String word) {
		HashMap<Character, Integer> charFreqMap = new HashMap<>();
		char[] charArray = word.toCharArray();

		// Populate the character frequency map
		for (char c: charArray) {
			if (charFreqMap.containsKey(c)) {
				charFreqMap.put(c, charFreqMap.get(c) + 1);
			} else {
				charFreqMap.put(c, 1);
			}
		}

		return charFreqMap;
	}
}