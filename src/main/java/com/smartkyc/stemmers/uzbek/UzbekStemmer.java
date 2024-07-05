package com.smartkyc.stemmers.uzbek;

import org.slf4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Code for this class has been converted from code in this repository
 * <a href="https://github.com/MaksudSharipov/UzbekStemmer">https://github.com/MaksudSharipov/UzbekStemmer</a>
 */
public class UzbekStemmer
{

	private static final String SUFFIX_CLASS = "SuffixClass";

	private static final String SUFFIX_NUMBER = "SuffixNumber";

	private static final String SUFFIX = "Suffix";

	private static final String FINAL = "Final";

	private static final Object[][] MATRIX_FSM_NOUN = new Object[][] {
			{ 'A', 0, new int[] { 1, 2, 3, 4, 5, 22, 23 }, new int[] { 6, 7, 8, 9, 10, 11, 12 }, new int[] { 13, 14, 15 },
					new int[] { 16 }, new int[] { 17 }, new int[] { 18, 19, 20, 21 }, 0, 0, 0, 0, 0, -1 },
			{ 'B', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ 'C', 0, new int[] { 1, 2, 3, 4, 5 }, 0, 0, 0, 0, 0, new int[] { 17 }, 0, 0, 0, 0, -1 },
			{ 'D', 0, new int[] { 1, 2, 3, 4, 5 }, new int[] { 6, 7, 8, 9, 10, 11, 12 }, 0, 0, 0, 0, new int[] { 17 }, 0, 0, 0, 0,
					-1 },
			{ 'E', 0, new int[] { 1, 2, 3, 4, 5 }, new int[] { 6, 7, 8, 9, 10, 11, 12 }, new int[] { 13, 14, 15 }, 0, 0, 0,
					new int[] { 17 }, 0, 0, 0, 0, -1 },
			{ 'F', 0, new int[] { 1, 2, 3, 4, 5, 22, 23 }, 0, 0, 0, 0, 0, 0, new int[] { 6, 7, 8, 9, 10, 11, 12 },
					new int[] { 13, 14, 15 }, new int[] { 16 }, new int[] { 18, 19, 20, 21 }, -1 },
			{ 'G', 0, new int[] { 1, 2, 3, 4, 5, 22, 23 }, 0, 0, 0, 0, 0, 0, new int[] { 6, 7, 8, 9, 10, 11, 12 },
					new int[] { 13, 14, 15 }, new int[] { 16 }, 0, -1 },
			{ 'H', 0, new int[] { 1 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ 'I', 0, new int[] { 1, 2, 3, 4, 5 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
			{ 'J', 0, new int[] { 1, 2, 3, 4, 5 }, 0, 0, 0, 0, 0, 0, new int[] { 6, 7, 8, 9, 10, 11, 12 }, 0, 0, 0, -2 },
			{ 'K', 0, new int[] { 1, 2, 3, 4, 5 }, 0, 0, 0, 0, 0, 0, new int[] { 6, 7, 8, 9, 10, 11, 12 }, new int[] { 13, 14, 15 },
					0, 0, -1 },
			{ 'L', 0, new int[] { 1, 2, 3, 4, 5, 22, 23 }, 0, 0, 0, 0, 0, 0, new int[] { 6, 7, 8, 9, 10, 11, 12 },
					new int[] { 13, 14, 15 }, new int[] { 16 }, 0, -2 } };

	private static final Object[][] MATRIX_FSM_TENSE_PERSON = new Object[][] {
			{ 'A', 0, new int[] { 1 }, new int[] { 2, 14, 15, 16, 17, 20, 21 }, new int[] { 3, 4, 6, 7 }, new int[] { 5 },
					new int[] { 9, 10, 12, 13 }, new int[] { 11 }, new int[] { 22 }, new int[] { 19 }, new int[] { 23, 24 }, 0, 0,
					-1 }, { 'B', 0, 0, new int[] { 8 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, //
			{ 'C', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, //
			{ 'D', 0, 0, new int[] { 1, 2 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2 },
			{ 'E', 0, new int[] { 1 }, new int[] { 2, 14, 15, 16, 17 }, new int[] { 6, 7 }, 0, 0, 0, 0, new int[] { 19 }, 0,
					new int[] { 12, 13 }, 0, -2 },
			{ 'F', 0, 0, new int[] { 8, 18, 20, 21 }, 0, 0, 0, 0, 0, 0, new int[] { 22, 23, 24 }, 0, 0, -2 },
			{ 'G', 0, 0, new int[] { 8 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2 },
			{ 'H', 0, new int[] { 1 }, new int[] { 2, 20, 21 }, new int[] { 3, 4, 6, 7 }, 0, 0, new int[] { 11 }, 0,
					new int[] { 19 }, 0, new int[] { 9, 10, 12, 13 }, new int[] { 5 }, -2 },
			{ 'I', 0, 0, new int[] { 18 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2 },
			{ 'J', 0, 0, new int[] { 20, 21 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2 },
			{ 'K', 0, 0, new int[] { 8, 18 }, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2 }, //
			{ 'L', 0, new int[] { 1 }, new int[] { 2 }, new int[] { 6, 7 }, 0, 0, 0, new int[] { 19 }, 0, new int[] { 12, 13 }, 0,
					-2 } };

	private static final Object[][] MATRIX_FSM_VERB = new Object[][] {
			{ 'A', 0, new int[] { 1, 3, 7, 8, 9, 10, 11, 12, 13 }, new int[] { 2, 4, 5, 6, 14, 15, 16, 17, 18, 19, 20, 21, 22 },
					new int[] { 23 }, -1 }, { 'B', 0, 0, new int[] { 19 }, 0, -1 }, //
			{ 'C', 0, 0, 0, 0, -1 }, //
			{ 'D', 0, new int[] { 1, 3 }, new int[] { 2, 4, 5, 6 }, 0, -2 } };

	private static final Object[][] MATRIX_FSM_RELATIVE = new Object[][] {
			{ 'A', 0, new int[] { 1, 3, 4, 5, 6, 8, 10, 11, 12 }, new int[] { 2 }, new int[] { 7, 9 }, new int[] { 13 }, 0, -1 },
			{ 'B', 0, 0, 0, 0, 0, 0, -1 }, //
			{ 'C', 0, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, 0, 0, 0, 0, -1 }, //
			{ 'D', 0, new int[] { 1, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, 0, 0, 0, new int[] { 2 }, -1 },
			{ 'E', 0, new int[] { 1, 3, 4, 5, 6, 8, 10, 11 }, new int[] { 2 }, new int[] { 7, 9 }, 0, 0, -1 },
			{ 'F', 0, new int[] { 3, 4, 6 }, 0, 0, 0, 0, -1 } };

	private static final Object[][] MATRIX_FSM_DERIVATIONAL = new Object[][] { { 'A', 0, new int[] { 1 }, new int[] { 2 },
			new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 22, 23, 26, 27, 28, 29, 30, 31, 32, 33, 34,
					35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 59, 60, 61, 62, 63,
					64, 65, 66, 67, 68, 69, 70, 71 }, new int[] { 21 }, new int[] { 24, 25 }, new int[] { 58 }, -1 },
			{ 'B', 0, 0, 0,
					new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 22, 23, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
							38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 }, 0, 0, 0, -1 }, //
			{ 'C', 0, 0, 0, new int[] { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, 0, 0, 0, -1 }, //
			{ 'D', 0, 0, 0, 0, 0, 0, 0, -1 }, //
			{ 'E', 0, 0, 0, new int[] { 16, 17, 18, 19, 20 }, 0, 0, 0, -1 }, //
			{ 'F', 0, 0, 0, new int[] { 22, 23 }, 0, 0, 0, -2 }, //
			{ 'G', 0, 0, 0,
					new int[] { 22, 23, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
							49, 50 }, 0, 0, 0, -1 } };

	private static final Object[][] MATRIX_FSM_NUMBER = new Object[][] {
			{ 'A', 0, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, -1 }, //
			{ 'B', 0, 0, -1 } };

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(UzbekStemmer.class);

	private Suffixes suffixes;

	public UzbekStemmer(final Function<InputStream, Suffixes> function)
	{
		loadSuffixes(function);
	}

	public String stem(final String word)
	{
		return stemUzbekWord(word);
	}

	private void loadSuffixes(final Function<InputStream, Suffixes> function)
	{
		try (final InputStream is = UzbekStemmer.class.getResourceAsStream("/UzbekStemmer/suffixes.xml")) {
			suffixes = function.apply(is);
		} catch (final Exception e) {
			log.error("Error loading Uzbek suffixes.");
		}
	}

	private String stemUzbekWord(final String word)
	{
		final List<String> words = new ArrayList<>();

		String tempWord = word;

		tempWord = noun(tempWord);

		tempWord = number(tempWord);

		words.add(tempWord);
		tempWord = word;

		tempWord = noun(tempWord);

		tempWord = derivational(tempWord);

		tempWord = prefixes(tempWord);

		words.add(tempWord);
		tempWord = word;

		addToWords(words, noun(tempWord));

		addToWords(words, tensePerson(word));

		int minWordsId = 0;
		if (words.get(1).length() < words.get(minWordsId).length()) {
			minWordsId = 1;
		}
		if (words.get(2).length() < words.get(minWordsId).length()) {
			minWordsId = 2;
		}
		if (words.get(3).length() < words.get(minWordsId).length()) {
			minWordsId = 3;
		}
		return words.get(minWordsId);
	}

	private String noun(final String word)
	{
		return rootWord(word, 5, MATRIX_FSM_NOUN);
	}

	private String tensePerson(final String word)
	{
		return rootWord(word, 1, MATRIX_FSM_TENSE_PERSON);
	}

	private String verb(final String word)
	{
		return rootWord(word, 2, MATRIX_FSM_VERB);
	}

	private String relative(final String word)
	{
		return rootWord(word, 3, MATRIX_FSM_RELATIVE);
	}

	private String derivational(final String word)
	{
		return rootWord(word, 4, MATRIX_FSM_DERIVATIONAL);
	}

	private String number(final String word)
	{
		return rootWord(word, 7, MATRIX_FSM_NUMBER);
	}

	private String prefixes(String word)
	{
		final int suffixesClassId = 6;
		final Map<Integer, Map<String, Object>> way = new HashMap<>();

		final int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7 };

		for (final int number : numbers) {
			final String suffix = suffixes.getSuffixes().get(suffixesClassId - 1).getElements().get(number - 1).getElementSuffix()
					.getSuffixValue();

			if (suffix.length() <= word.length() && suffix.equals(
					word.substring(0, suffix.length())) && word.length() - suffix.length() > 1) {
				final String oldWord = word;
				word = word.substring(suffix.length());

				boolean mistake = false;
				for (final String br : suffixes.getSuffixes().get(suffixesClassId - 1).getElements().get(number - 1).getPass()
						.getBrList().stream().map(Suffixes.Suffix.Element.Br::getBrText).collect(Collectors.toList())) {
					if (br.length() <= word.length() && br.equals(word.substring(0, br.length()))) {
						mistake = true;
						break;
					}
				}
				if (mistake) {
					word = oldWord;
					continue;
				}

				final Map<String, Object> transition = immutableMap( //
						SUFFIX_CLASS, "Preffixes", //
						SUFFIX_NUMBER, number, //
						SUFFIX, suffix, //
						FINAL, true);
				way.put(way.size() + 1, transition);
			}
		}
		return word;
	}

	private String rootWord(String word, final int suffixesClassId, final Object[][] matrixFSM)
	{
		final Map<Integer, Map<String, Object>> way = new HashMap<>();

		boolean stop = false;
		int x = 0;
		final List<String> minWords = immutableList("u", "bu", "ol", "uq");

		final List<Suffixes.Suffix> allSuffixes = suffixes.getSuffixes();
		while (x < matrixFSM.length) {
			int y = 1;
			while (x >= 0 && y < matrixFSM[x].length) {
				if (matrixFSM[x][y] instanceof int[]) {
					final int[] integers = (int[]) matrixFSM[x][y];
					for (final int number : integers) {
						for (final String suffix : getSuffixList(suffixesClassId, number)) {
							if (checkSuffix(word, suffix)) {
								final String oldWord = word;
								word = cutSuffix(word, suffix);

								boolean mistake = false;
								final List<Suffixes.Suffix.Element.Br> passes = allSuffixes.get(suffixesClassId - 1).getElements()
										.get(number - 1).getPass().getBrList();
								final List<String> passesAsStrings = passes.stream() //
										.map(Suffixes.Suffix.Element.Br::getBrText) //
										.collect(toImmutableList());
								for (final String pass : passesAsStrings) {
									if (checkSuffix(word, pass)) {
										mistake = true;
										break;
									}
								}

								if (mistake) {
									word = oldWord;
									continue;
								}
								if (word.length() > 2 || minWords.contains(word)) {
									x = y - 1;
									y = 0;

									final boolean finalVal = x < 0 || !matrixFSM[x][matrixFSM[x].length - 1].equals(-2);

									final Map<String, Object> transition = immutableMap( //
											SUFFIX_CLASS, allSuffixes.get(suffixesClassId - 1).getSuffixClass(), //
											SUFFIX_NUMBER, number, //
											SUFFIX, suffix, //
											FINAL, finalVal);

									way.put(way.size() + 1, transition);

									final List<Suffixes.Suffix.Element.Br> joins = allSuffixes.get(suffixesClassId - 1)
											.getElements().get(number - 1).getJoin().getBrList();
									final List<String> joinsAsStrings = joins.stream() //
											.map(Suffixes.Suffix.Element.Br::getBrText) //
											.collect(toImmutableList());
									for (final String join : joinsAsStrings) {
										if (checkSuffix(word, join) && cutSuffix(word, join).length() > 2 || minWords.contains(
												cutSuffix(word, join))) {
											word = cutSuffix(word, join);

											way.put(way.size() + 1, immutableMap( //
													SUFFIX_CLASS, "Exception", //
													SUFFIX_NUMBER, "null", //
													SUFFIX, suffix, //
													FINAL, true));
										}
									}
								} else {
									word = oldWord;
									stop = true;
								}
								break;
							}
						}
					}
				} else if (matrixFSM[x][y].equals(-1)) {
					stop = true;
					break;
				} else if (matrixFSM[x][y].equals(-2)) {
					final int i = way.size();
					final StringBuilder wordBuilder = new StringBuilder(word);
					for (int k = i; k > 0; k--) {
						final Map<String, Object> valx = way.get(k);
						if (valx.get(FINAL).equals(false)) {
							wordBuilder.append(valx.get(SUFFIX));
							way.remove(k);
						} else {
							break;
						}
					}
					word = wordBuilder.toString();
					stop = true;
					break;
				}
				y = y + 1;
			}
			if (stop) {
				break;
			}
			x++;
		}
		return word;

	}

	private List<String> getSuffixList(final int suffixesClassId, final int number)
	{
		final List<Suffixes.Suffix.Element.ElementSuffix.Allomorph> allomorphList = suffixes.getSuffixes().get(suffixesClassId - 1)
				.getElements().get(number - 1).getElementSuffix().getAllomorphList();
		final List<String> suffixList;
		if (!suffixes.getSuffixes().get(suffixesClassId - 1).getElements().get(number - 1).getElementSuffix()
				.isAllomorphAtribute()) {
			suffixList = immutableList(
					suffixes.getSuffixes().get(suffixesClassId - 1).getElements().get(number - 1).getElementSuffix()
							.getSuffixValue());
		} else {
			suffixList = allomorphList.stream() //
					.map(Suffixes.Suffix.Element.ElementSuffix.Allomorph::getAllomorphValue) //
					.collect(toImmutableList());
		}
		return suffixList;
	}

	private boolean checkSuffix(final String word, final String suffix)
	{
		return word.length() >= suffix.length() && suffix.equals(word.substring(word.length() - suffix.length()));
	}

	private String cutSuffix(final String word, final String suffix)
	{
		if (checkSuffix(word, suffix)) {
			return word.substring(0, word.length() - suffix.length());
		}
		return word;
	}

	private void addToWords(final List<String> words, final String word)
	{
		String tempWord = word;

		tempWord = verb(tempWord);
		tempWord = relative(tempWord);
		tempWord = derivational(tempWord);
		tempWord = prefixes(tempWord);

		words.add(tempWord);
	}

	@SafeVarargs
	private static <E> List<E> immutableList(final E e, final E... others)
	{
		final List<E> list = new ArrayList<>(others.length + 1);
		list.add(e);
		list.addAll(Arrays.asList(others));
		return Collections.unmodifiableList(list);
	}

	private static <E> Collector<E, ?, List<E>> toImmutableList()
	{
		return Collector.<E, ArrayList<E>, List<E>>of( //
				ArrayList::new, //
				ArrayList::add, //
				(l1, l2) -> {
					l1.addAll(l2);
					return l1;
				}, //
				Collections::unmodifiableList, //
				Collector.Characteristics.CONCURRENT);
	}

	@SuppressWarnings({ "SameParameterValue", "java:S107" })
	private static <K, V> Map<K, V> immutableMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4)
	{
		Map<K, V> map = new LinkedHashMap<>();
		map.put(k1, v1);
		map.put(k2, v2);
		map.put(k3, v3);
		map.put(k4, v4);
		return Collections.unmodifiableMap(map);
	}

}
