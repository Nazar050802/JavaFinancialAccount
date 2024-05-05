package cz.cuni.mff.mozharon.financialaccounting.application.utils;

import cz.cuni.mff.mozharon.financialaccounting.domain.entities.StatisticField;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class for extracting and manipulating statistical data.
 */
public class StatisticsUtils {

    /**
     * Extracts a map where the keys are years as integers and the values are income values.
     * @param statistics A map from integer years to StatisticField objects.
     * @return A map with integer year keys and double income values.
     */
    public static Map<Integer, Double> extractYearsIncome(Map<Integer, StatisticField> statistics) {
        return statistics.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getIncome()
                ));
    }

    /**
     * Extracts a map where the keys are years as integers and the values are expense values.
     * @param statistics A map from integer years to StatisticField objects.
     * @return A map with integer year keys and double expense values.
     */
    public static Map<Integer, Double> extractYearsExpense(Map<Integer, StatisticField> statistics) {
        return statistics.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getExpense()
                ));
    }

    /**
     * Extracts a map where the keys are years as integers and the values are profit values.
     * @param statistics A map from integer years to StatisticField objects.
     * @return A map with integer year keys and double profit values.
     */
    public static Map<Integer, Double> extractYearsProfit(Map<Integer, StatisticField> statistics) {
        return statistics.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getProfitability()
                ));
    }

    /**
     * Extracts a map where the keys are months as integers and the values are income values for a given year.
     * @param statistics A nested map where the outer keys are years and inner keys are months, mapped to StatisticField objects.
     * @param year The year for which to extract monthly income statistics.
     * @return A map with integer month keys and double income values for the specified year.
     */
    public static Map<Integer, Double> extractMonthlyIncome(Map<Integer, Map<Integer, StatisticField>> statistics, int year) {
        Map<Integer, StatisticField> yearlyStats = statistics.get(year);
        if (yearlyStats != null) {
            return yearlyStats.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().getIncome()
                    ));
        } else {
            return Map.of();
        }
    }

    /**
     * Extracts a map where the keys are months as integers and the values are expense values for a given year.
     * @param statistics A nested map where the outer keys are years and inner keys are months, mapped to StatisticField objects.
     * @param year The year for which to extract monthly expense statistics.
     * @return A map with integer month keys and double expense values for the specified year.
     */
    public static Map<Integer, Double> extractMonthlyExpense(Map<Integer, Map<Integer, StatisticField>> statistics, int year) {
        Map<Integer, StatisticField> yearlyStats = statistics.get(year);
        if (yearlyStats != null) {
            return yearlyStats.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().getExpense()
                    ));
        } else {
            return Map.of();
        }
    }

    /**
     * Extracts a map where the keys are months as integers and the values are profit values for a given year.
     * @param statistics A nested map where the outer keys are years and inner keys are months, mapped to StatisticField objects.
     * @param year The year for which to extract monthly profit statistics.
     * @return A map with integer month keys and double profit values for the specified year.
     */
    public static Map<Integer, Double> extractMonthlyProfit(Map<Integer, Map<Integer, StatisticField>> statistics, int year) {
        Map<Integer, StatisticField> yearlyStats = statistics.get(year);
        if (yearlyStats != null) {
            return yearlyStats.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().getProfitability()
                    ));
        } else {
            return Map.of();
        }
    }

    /**
     * Extracts a map where the keys are days as integers and the values are income values for a given year and month.
     * @param statistics A nested map where the outer keys are years, the middle keys are months, and inner keys are days, mapped to StatisticField objects.
     * @param year The year for which to extract daily income statistics.
     * @param month The month for which to extract daily income statistics.
     * @return A map with integer day keys and double income values for the specified year and month.
     */
    public static Map<Integer, Double> extractDailyIncome(Map<Integer, Map<Integer, Map<Integer, StatisticField>>> statistics, int year, int month) {
        Map<Integer, Map<Integer, StatisticField>> yearlyStats = statistics.get(year);
        if (yearlyStats != null) {
            Map<Integer, StatisticField> monthlyStats = yearlyStats.get(month);
            if (monthlyStats != null) {
                return monthlyStats.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().getIncome()
                        ));
            }
        }
        return Map.of(); 
    }

    /**
     * Extracts a map where the keys are days as integers and the values are expense values for a given year and month.
     * @param statistics A nested map where the outer keys are years, the middle keys are months, and inner keys are days, mapped to StatisticField objects.
     * @param year The year for which to extract daily expense statistics.
     * @param month The month for which to extract daily expense statistics.
     * @return A map with integer day keys and double expense values for the specified year and month.
     */
    public static Map<Integer, Double> extractDailyExpense(Map<Integer, Map<Integer, Map<Integer, StatisticField>>> statistics, int year, int month) {
        Map<Integer, Map<Integer, StatisticField>> yearlyStats = statistics.get(year);
        if (yearlyStats != null) {
            Map<Integer, StatisticField> monthlyStats = yearlyStats.get(month);
            if (monthlyStats != null) {
                return monthlyStats.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().getExpense()
                        ));
            }
        }
        return Map.of();
    }

    /**
     * Extracts a map where the keys are days as integers and the values are profit values for a given year and month.
     * @param statistics A nested map where the outer keys are years, the middle keys are months, and inner keys are days, mapped to StatisticField objects.
     * @param year The year for which to extract daily profit statistics.
     * @param month The month for which to extract daily profit statistics.
     * @return A map with integer day keys and double profit values for the specified year and month.
     */
    public static Map<Integer, Double> extractDailyProfit(Map<Integer, Map<Integer, Map<Integer, StatisticField>>> statistics, int year, int month) {
        Map<Integer, Map<Integer, StatisticField>> yearlyStats = statistics.get(year);
        if (yearlyStats != null) {
            Map<Integer, StatisticField> monthlyStats = yearlyStats.get(month);
            if (monthlyStats != null) {
                return monthlyStats.entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().getProfitability()
                        ));
            }
        }
        return Map.of();
    }

    /**
     * Sorts a map with integer keys and double values based on the key values,
     * then converts the keys to strings.
     * @param map A map with integer keys and double values.
     * @param ascending A boolean flag that determines the sorting order (true for ascending, false for descending).
     * @return A sorted map with string keys and double values, maintaining the order specified by the sorting.
     */
    public static Map<String, Double> sortKeysAndConvertToString(Map<Integer, Double> map, boolean ascending) {
        return map.entrySet()
                .stream()
                .sorted(ascending ?
                        Map.Entry.comparingByKey() :
                        Map.Entry.<Integer, Double>comparingByKey().reversed())
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toString(),
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
