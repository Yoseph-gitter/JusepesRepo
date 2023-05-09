# Algorithms Repo
Github Repo for managing interview questions and saving them.
import java.util.Comparator;
import java.util.function.BiConsumer;

public class GenericSorterFunctional<T> {
    /**
     * Typical Bubble Up sort Algorithms
     *
     * @param arr
     */
    protected BiConsumer<T[], Comparator<T>> bubbleSort = (T[] arr, Comparator<T> comparator) -> {
        //sanitize input
        validateInput(arr);
        //printArr(arr);
        for (int limit = arr.length - 1; limit > 0; limit--) {
            for (int i = 1; i <= limit; i++) {
                if (comparator.compare(arr[i - 1], (arr[i])) > 0) {
                    swap(arr, i - 1, i);
                }
            }
            //System.out.println("PASS : " + limit);
        }
        printArr(arr);
    };
    protected BiConsumer<T[], Comparator<T>> bubbleSortOptimized = (T[] arr, Comparator<T> comparator) -> {
        //sanitize input
        validateInput(arr);
        boolean hasBeenSwapped = false;
        for (int limit = arr.length - 1; limit > 0; limit--) {
            for (int i = 1; i <= limit; i++) {
                if (comparator.compare(arr[i - 1], (arr[i])) > 0) {
                    swap(arr, i - 1, i);
                    hasBeenSwapped = true;
                }
            }
            // when there has not been a swap in a pass i.e its already sorted
            if (!hasBeenSwapped) {
                break;
            }
        }
        printArr(arr);
    };
    
    /**
     * In each iteration or pass there would be a partially sorted section and unsorted section
     * Each element is compared with all the rest of partially sorted elements
     * If its already sorted in O(n) we can determine if its sorted or not.
     *
     * @param arr
     * @param comparator
     */
    protected BiConsumer<T[], Comparator<T>> insertionSort = (T[] arr, Comparator<T> comparator) -> {
        //sanitize input
        validateInput(arr);
        for (int pass = 1; pass < arr.length; pass++) {
            for (int i = pass; i > 0; i--) {
                if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                    swap(arr, i, i - 1);
                } else {
                    //if no swap it means exit
                    break;
                }
            }
        }
        printArr(arr);
        
    };
    
    /**
     * Pick an element and compare with the min value on the right side if its the min swap or else do nothing
     * Here there would be one element sorted in its final position.
     *
     * @param arr
     * @param comparator
     */
    protected BiConsumer<T[], Comparator<T>> selectionSort = (T[] arr, Comparator<T> comparator) -> {
        //sanitize input
        validateInput(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            //find min value from j to end of array
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            //min is found so swap it with index i
            swap(arr, i, minIndex);
        }
        printArr(arr);
    };
    
    public static void main(String[] args) {
        int SIZE_TO_SORT = 150;
        GenericSorterFunctional<Integer> sorrter = new GenericSorterFunctional<>();
        Integer[] array = sorrter.generateRandomData(SIZE_TO_SORT);
        
        Integer[] copyArr = sorrter.copyArr(array);
        System.out.println(String.format("Sorting mechanism %s", "bubbleSort"));
        sorrter.doSort(sorrter.bubbleSort, copyArr, getIntegerComparator());
        // sorrter.printArr(array);
        //Collections.reverse(Arrays.asList(array));
        copyArr = sorrter.copyArr(array);
        System.out.println(String.format("Sorting mechanism %s", "bubbleSortOptimized"));
        sorrter.doSort(sorrter.bubbleSortOptimized, copyArr, getIntegerComparator());
        //
        //Collections.reverse(Arrays.asList(array));
        copyArr = sorrter.copyArr(array);
        System.out.println(String.format("Sorting mechanism %s", "selectionSort"));
        sorrter.doSort(sorrter.selectionSort, copyArr, getIntegerComparator());
        
        //Collections.reverse(Arrays.asList(array));
        copyArr = sorrter.copyArr(array);
        System.out.println(String.format("Sorting mechanism %s", "insertionSort"));
        sorrter.doSort(sorrter.insertionSort, copyArr, getIntegerComparator());
    }
    
    private static Comparator<Integer> getIntegerComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
    }
    
    private void doSort(BiConsumer<T[], Comparator<T>> consumerToCall, T[] arr, Comparator<T> comparator) {
        long startTime = System.currentTimeMillis();
        new Sorter<T>() {
            @Override
            public void sort() {
                consumerToCall.accept(arr, comparator);
            }
        }.sort();
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Time Taken to Sort is : %s", (endTime - startTime)));
    }
    
    private Integer[] generateRandomData(int size) {
        Integer[] data = new Integer[size];
        int index = 0;
        do {
            data[index++] = (int) Math.floor(Math.random() * (size));
        } while (index < size);
        return data;
    }
    
    private void swap(T[] arr, int pass, int i) {
        T temp = arr[pass];
        arr[pass] = arr[i];
        arr[i] = temp;
    }
    
    private void validateInput(T[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Input arr is null or Emtpy");
            return;
        }
    }
    
    private void printArr(T[] arr) {
        for (T a : arr) {
            System.out.print(a + "->");
        }
        System.out.println();
    }
    
    private Integer[] copyArr(Integer[] arr) {
        Integer[] copyArr = new Integer[arr.length];
        int index = 0;
        for (Integer e : arr) {
            copyArr[index++] = e;
        }
        return copyArr;
    }
}
import java.util.ArrayList;
import java.util.Comparator;

public class GenericSorter<T extends Object> {
    
    
    public static void main(String[] args) {
        Integer[] arr = {4, 5, 6, 1, 2};
        //descending arr
        Integer[] arr1 = {6, 5, 4, 2, 1};
        //ascending arr
        Integer[] arr2 = {1, 2, 3, 4, 5, 6};
        //
        GenericSorter<Integer> sorrter = new GenericSorter<>();
        Integer[] array = sorrter.generateRandomData(100);
        sorrter.printArr(array);
        sorrter.insertionSort(array, getIntegerComparator());
        sorrter.printArr(array);
        //
        array = sorrter.generateRandomData(100);
        sorrter.printArr(array);
        sorrter.bubbleSort(array, getIntegerComparator());
        sorrter.printArr(array);
        //
        array = sorrter.generateRandomData(100);
        sorrter.printArr(array);
        sorrter.selectionSort(array, getIntegerComparator());
        sorrter.printArr(array);
        //
        array = sorrter.generateRandomData(100);
        sorrter.printArr(array);
        ArrayList<Integer> merged= sorrter.mergeSort(array, getIntegerComparator());
        Integer[] mergedArr= new Integer[merged.size()] ;
        sorrter.printArr(merged.toArray(mergedArr));
    }
    
    private static Comparator<Integer> getIntegerComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
    }
    
    public ArrayList<T> mergeSort(T[] arr, Comparator<T> comparator) {
        return mergeSort(arr, comparator, 0, arr.length - 1);
    }
    
    private ArrayList<T> mergeSort(T[] arr, Comparator<T> comparator, int start, int end) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        if (start == end) {
            ArrayList<T> arrayList = new ArrayList<>();
            arrayList.add(arr[start]);
            return arrayList;
        }
        int mid = (start + end) / 2;
        ArrayList<T> left = mergeSort(arr, comparator, start, mid);
        ArrayList<T> right = mergeSort(arr, comparator, mid + 1, end);
        return merge(left, right, comparator);
    }
    
    private ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right, Comparator<T> comparator) {
        ArrayList<T> merged = new ArrayList();
        int leftIndex = 0;
        int rightIndex = 0;
        int current = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) < 0l) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }
        //if left is merged already
        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }
        //if right is merged already
        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }
        return merged;
    }
    
    /**
     * Typical Bubble Up sort Algorithms
     *
     * @param arr
     */
    public void bubbleSort(T[] arr, Comparator<T> comparator) {
        //sanitize input
        validateInput(arr);
        System.out.println("Before Sorted");
        printArr(arr);
        for (int limit = arr.length - 1; limit > 0; limit--) {
            for (int i = 1; i <= limit; i++) {
                if (comparator.compare(arr[i - 1], (arr[i])) > 0) {
                    swap(arr, i - 1, i);
                }
            }
            System.out.println("PASS : " + limit);
        }
        System.out.println("After Sorted");
        printArr(arr);
    }
    
    /**
     * Pick an element and compare with the min value on the right side if its the min swap or else do nothing
     * Here there would be one element sorted in its final position.
     *
     * @param arr
     * @param comparator
     */
    public void selectionSort(T[] arr, Comparator<T> comparator) {
        //sanitize input
        validateInput(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            //find min value from j to end of array
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            //min is found so swap it with index i
            swap(arr, i, minIndex);
        }
    }
    
    /**
     * In each iteration or pass there would be a partially sorted section and unsorted section
     * Each element is compared with all the rest of partially sorted elements
     * If its already sorted, in O(n) we can determine if its sorted or not.
     *
     * @param arr
     * @param comparator
     */
    public void insertionSort(T[] arr, Comparator<T> comparator) {
        //sanitize input
        validateInput(arr);
        for (int pass = 1; pass < arr.length; pass++) {
            for (int i = pass; i > 0; i--) {
                if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                    swap(arr, i, i - 1);
                } else {
                    //if no swap it means
                    break;
                }
            }
        }
    }
    
    private Integer[] generateRandomData(int size) {
        Integer[] data = new Integer[size];
        int index = 0;
        do {
            data[index++] = (int) Math.floor(Math.random() * (size));
        } while (index < size);
        return data;
    }
    
    private void swap(T[] arr, int pass, int i) {
        T temp = arr[pass];
        arr[pass] = arr[i];
        arr[i] = temp;
    }
    
    private void validateInput(T[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Input arr is null or Emtpy");
            return;
        }
    }
    
    private void printArr(T[] arr) {
        for (T a : arr) {
            System.out.print(a + "->");
        }
        System.out.println();
    }
    
}
//
  import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataTypeCaster {
    public static boolean isDouble(String strNum) {
        String digits = getDigits(strNum);
        return digits.length() + 1 == strNum.length() && strNum.contains(".");
    }
    
    public static boolean isLong(String strNum) {
        String digits = getDigits(strNum);
        return digits.length() > 0 && digits.length() == strNum.length();
    }
    
    public static boolean isBoolean(String value) {
        return Boolean.valueOf(value);
    }
    
    public static boolean isString(String value) {
        return !isNumeric(value) && !isBoolean(value) && !isLocalDate(value) && !isLocalDateTime(value);
    }
    
    public static boolean isLocalDate(String strNum) {
        try {
            LocalDate localDate = LocalDate.parse(strNum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static boolean isLocalDateTime(String strNum) {
        try {
            LocalDateTime localDate = LocalDateTime.parse(strNum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        } else {
            int sz = str.length();
            
            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            
            return true;
        }
    }
    
    public static String getDigits(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); ++i) {
            if (Character.isDigit(str.charAt(i))) {
                buffer.append(str.charAt(i));
            }
        }
        return buffer.toString();
    }
    
    public static Object checkType(String value) {
        Object output = null;
        if (isNumeric(value)) {
            if (isDouble(value)) {
                output = Double.valueOf(value);
                System.out.println("Double : " + value);
            } else if (isLong(value)) {
                output = Long.valueOf(value);
                System.out.println("Long : " + value);
            }
        } else if (isLocalDate(value)) {
            output = LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
            System.out.println("LocalDate : " + value);
        } else if (isLocalDateTime(value)) {
            output = LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
            System.out.println("LocalDateTime Value : " + value);
        } else if (isBoolean(value)) {
            output = Boolean.valueOf(value);
            System.out.println("Boolean Value : " + value);
        } else {
            output = value;
            System.out.println("String Value : " + value);
        }
        return output;
    }
    
    public static Class getClassType(String value) {
        return checkType(value).getClass();
    }
}
//
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class CsvReader {
    private static String DEFAULT_SEPARATER = ",";
    private Map<String, String> valueToLineMap = new HashMap<>();
    
    public CsvReader() {
    }
    
    public static void main(String[] args) throws IOException {
        String INPUT_FILE = "src/testFile2.csv";
        String outPutFile = System.getProperty("user.dir") + "/src/sortedOutFile.csv";
        CsvReader reader = new CsvReader();
        //reader.readCsvRandomAccess(INPUT_FILE);
        reader.sortCsvFile(INPUT_FILE, outPutFile, 0);
        boolean isEqual = reader.compareTwoSortedCsvs(INPUT_FILE, outPutFile);
        System.out.println("Are files equal " + isEqual);
    }
    
    public void sortCsvFile(String inputFile, String outputFile, int byIndex) {
        try {
            // List<String> list = readCsvFile(inputFile);
            List<String> list = readCsvRandomAccess(inputFile);
            //sorted key with associated value
            ArrayList<Object> sortedList = sortByIndex(list, byIndex, DEFAULT_SEPARATER);
            //sort the file based on sorted fields
            writeSortedFile(sortedList, outputFile);
            printArr(sortedList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean compareTwoSortedCsvs(String right, String left) {
        try {
            BufferedReader bufferedReaderRight = new BufferedReader(new FileReader(right));
            BufferedReader bufferedReaderLeft = new BufferedReader(new FileReader(left));
            String currentLineRight = null;
            String currentLineLeft = null;
            while ((currentLineRight = bufferedReaderRight.readLine()) != null &&
                    (currentLineLeft = bufferedReaderLeft.readLine()) != null) {
                if (!currentLineRight.equalsIgnoreCase(currentLineLeft)) {
                    return false;
                }
            }
            if (currentLineRight != null || currentLineLeft != null) {
                return false;
            }
        } catch (FileNotFoundException e) {
        
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    
    public ArrayList<Object> sortByIndex(
            List<String> readLines, int index, String separater) throws Exception {
        
        if (readLines != null && readLines.get(0).split(separater).length <= index) {
            throw new RuntimeException("Index can not be out side of range of csv fields");
        }
        //determine what dataType to sort
        Object object = DataTypeCaster.checkType(readLines.get(0).split(separater)[index]);
        Comparator<? extends Object> comparator = getComparator(object);
        //now sort the value in array
        Object[] arrayToSort = null;
        GenericSorter sorter = null;
        if (object instanceof Long) {
            sorter = new GenericSorter<Long>();
            arrayToSort = new Long[readLines.size()];
        } else if (object instanceof Double) {
            sorter = new GenericSorter<Double>();
            arrayToSort = new Double[readLines.size()];
        } else if (object instanceof String) {
            sorter = new GenericSorter<String>();
            arrayToSort = new String[readLines.size()];
        } else if (object instanceof LocalDate) {
            sorter = new GenericSorter<LocalDate>();
            arrayToSort = new LocalDate[readLines.size()];
        } else if (object instanceof LocalDateTime) {
            sorter = new GenericSorter<LocalDateTime>();
            arrayToSort = new LocalDateTime[readLines.size()];
        } else {
            throw new Exception("No Known DataType used ...kindly specify the type");
        }
        int count = 0;
        for (String line : readLines) {
            String[] splits = line.trim().split(separater);
            this.valueToLineMap.put(splits[index], line);
            arrayToSort[count++] = splits[index];
        }
        ArrayList<Object> values = (ArrayList<Object>) sorter.mergeSort(arrayToSort, comparator);
        return values;
    }
    
    public List<String> readCsvFile(String FILE_NAME_PATH) {
        List<String> lines = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME_PATH));
            String currentLine = bufferedReader.readLine();
            if (isHeader(currentLine)) {
                currentLine = null;
            }
            System.out.println("Print Header : " + currentLine);
            while ((currentLine = bufferedReader.readLine()) != null) {
                lines.add(currentLine);
                System.out.println(currentLine);
            }
        } catch (FileNotFoundException e) {
        
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
    
    public List<String> readCsvRandomAccess(String FILE_NAME_PATH, int startIndex, int SIZE_TO_READ) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(FILE_NAME_PATH, "r");
        int n = (startIndex ) * SIZE_TO_READ;
        String line;
        try (Stream<String> lines = Files.lines(Paths.get(FILE_NAME_PATH))) {
            line = lines.skip(n).find.get();
            System.out.println(line);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
    private boolean isHeader(String line) {
        String[] vals = line.split(",");
        for (String v : vals) {
            if (!DataTypeCaster.isString(v)) {
                return false;
            }
        }
        return true;
    }
    
    public void writeSortedFile(ArrayList<Object> sortedValues, String fileToWrite) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileToWrite));
            for (Object key : sortedValues) {
                System.out.println(valueToLineMap.get(key));
                writer.write(valueToLineMap.get(key));
                writer.newLine();
            }
        } catch (IOException e) {
        
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            
            }
        }
    }
    
    private Comparator<? extends Object> getComparator(Object object, String... regex) {
        if (object instanceof Long) {
            return new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return o1.compareTo(o2);
                }
            };
        } else if (object instanceof Double) {
            return new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return o1.compareTo(o2);
                }
            };
        } else if (object instanceof String) {
            return new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            };
        } else if (object instanceof LocalDate) {
            return new Comparator<LocalDate>() {
                @Override
                public int compare(LocalDate o1, LocalDate o2) {
                    return o1.compareTo(o2);
                }
            };
        } else if (object instanceof LocalDateTime) {
            return new Comparator<LocalDateTime>() {
                @Override
                public int compare(LocalDateTime o1, LocalDateTime o2) {
                    return o1.compareTo(o2);
                }
            };
        } else if (regex.length > 0 && object.toString().matches(regex[0])) {
            return null;
        } else {
            return null;
        }
    }
    
    private void printArr(ArrayList<Object> arr) {
        for (Object a : arr) {
            System.out.print(a + "->");
        }
        System.out.println();
    }
}
  
