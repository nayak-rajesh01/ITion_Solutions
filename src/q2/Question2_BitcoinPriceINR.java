package q2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Question 2: Fetch Bitcoin price in INR via CoinGecko REST API and print it in words.
 *
 * Approach:
 * - Use java.net.HttpURLConnection to make a GET request to the CoinGecko API.
 * - Manually parse the JSON response (no external library) to extract the INR value.
 * - Convert the integer to Indian number words using the Indian numbering system:
 *     Units, Tens, Hundreds, Thousands, Lakhs, Crores.
 *
 * Indian Number System:
 *   1,00,00,000 = 1 Crore
 *      1,00,000 = 1 Lakh
 *        1,000  = 1 Thousand
 *          100  = 1 Hundred
 *
 * Time Complexity: O(log N) for number-to-words conversion.
 * Space Complexity: O(1) extra space.
 */
public class Question2_BitcoinPriceINR {

    // --- Indian number-to-words conversion ---

    static final String[] ones = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"
    };

    /** Convert a number < 100 to words. */
    static String twoDigits(int n) {
        if (n == 0) return "";
        if (n < 20) return ones[n];
        return tens[n / 10] + (n % 10 != 0 ? " " + ones[n % 10] : "");
    }

    /** Convert a number < 1000 to words. */
    static String threeDigits(int n) {
        if (n == 0) return "";
        if (n < 100) return twoDigits(n);
        return ones[n / 100] + " Hundred" + (n % 100 != 0 ? " and " + twoDigits(n % 100) : "");
    }

    /**
     * Converts a non-negative integer to Indian number words.
     * Handles up to 99,99,99,999 (99 Crore 99 Lakh 99 Thousand 9 Hundred 99).
     */
    static String toIndianWords(long number) {
        if (number == 0) return "Zero";

        StringBuilder result = new StringBuilder();

        long crore    = number / 10_000_000;
        long lakh     = (number % 10_000_000) / 100_000;
        long thousand = (number % 100_000) / 1_000;
        long rem      = number % 1_000;

        if (crore > 0) {
            result.append(twoDigits((int) crore)).append(" Crore ");
        }
        if (lakh > 0) {
            result.append(twoDigits((int) lakh)).append(" Lakh ");
        }
        if (thousand > 0) {
            result.append(twoDigits((int) thousand)).append(" Thousand ");
        }
        if (rem > 0) {
            result.append(threeDigits((int) rem));
        }

        return result.toString().trim();
    }

    // --- HTTP GET request ---

    static String httpGet(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);
        conn.setRequestProperty("Accept", "application/json");

        int status = conn.getResponseCode();
        if (status != 200) throw new RuntimeException("HTTP " + status);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        return sb.toString();
    }

    /** Very simple JSON field extractor — no external library needed. */
    static long extractLong(String json, String key) {
        // Looks for: "key":VALUE
        String search = "\"" + key + "\":";
        int idx = json.indexOf(search);
        if (idx == -1) throw new RuntimeException("Key not found: " + key);
        int start = idx + search.length();
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '.')) {
            end++;
        }
        String raw = json.substring(start, end);
        return (long) Double.parseDouble(raw);
    }

    public static void main(String[] args) {
        System.out.println("=== Question 2: Bitcoin Price in INR (in Words) ===\n");

        String apiUrl = "https://api.coingecko.com/api/v3/simple/price"
                + "?ids=bitcoin&vs_currencies=usd,inr,eur";

        try {
            System.out.println("Fetching Bitcoin prices from CoinGecko API...");
            String json = httpGet(apiUrl);
            System.out.println("Raw API Response: " + json + "\n");

            long inrPrice = extractLong(json, "inr");
            long usdPrice = extractLong(json, "usd");
            long eurPrice = extractLong(json, "eur");

            System.out.println("Bitcoin Prices:");
            System.out.printf("  USD : $%,d%n", usdPrice);
            System.out.printf("  EUR : €%,d%n", eurPrice);
            System.out.printf("  INR : ₹%,d%n", inrPrice);
            System.out.println();
            System.out.println("Bitcoin Price in INR (in words):");
            System.out.println("  ₹" + inrPrice + "  →  " + toIndianWords(inrPrice));

        } catch (Exception e) {
            System.out.println("Could not reach CoinGecko API: " + e.getMessage());
            System.out.println("Demonstrating with example value ₹78,23,123:\n");
            long example = 7823123L;
            System.out.println("  ₹" + example + "  →  " + toIndianWords(example));
        }

        // Always demonstrate with a few examples
        System.out.println("\n--- Conversion examples ---");
        long[] demos = {7823123L, 100000L, 10000000L, 85000000L, 999999999L};
        for (long n : demos) {
            System.out.printf("  %-15d → %s%n", n, toIndianWords(n));
        }
    }
}

