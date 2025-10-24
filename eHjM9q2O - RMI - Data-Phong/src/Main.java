import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService dataService = (DataService) registry.lookup("RMIDataService");
        String studentCode = "B22DCCN613";
        String qCode = "eHjM9q2O";
        Object object = dataService.requestData(studentCode, qCode);
        String parts = (String) object;
        String[] partsArray = parts.split("[,\\s]+");
        int[] arr = new int[partsArray.length];
        int[] nextArr = new int[partsArray.length];
        for (int i = 0; i < partsArray.length; i++) {
            arr[i] = Integer.parseInt(partsArray[i]);
        }

        nextArr = nextPermutation(arr);
        String result = Arrays.stream(nextArr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        dataService.submitData(studentCode, qCode, result);
    }

    private static int[] nextPermutation(int[] arr) {
        int n = arr.length;
        int i = n - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) i--;
        if (i >= 0) {
            int j = n - 1;
            while (arr[j] <= arr[i]) j--;
            swap(arr, i, j);
        }
        reverse(arr, i + 1, n - 1);
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(int[] arr, int l, int r) {
        while (l < r) swap(arr, l++, r--);
    }
}