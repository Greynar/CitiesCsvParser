package CityBook;

import CityBook.model.City;
import static CityBook.utils.CityUtils.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<City> cities = parse("./src/main/resources/city_ru.csv"); // получение списка городов из файла

        countCityInRegions(cities);
    }
    /**
     * Подсчет количества городов в каждом регионе
     * создание Map регионов, в которую записываются пара ключ : значение,
     * где ключ - название региона, а значение - количество городов
     *
     * @param cities список городов
     */
    private static void countCityInRegions(List<City> cities) {
        Map<String, Long> regions = cities.stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting())); // группировка по региону и подсчет количества городов в нем
        regions.forEach((k, v) -> System.out.println(k + " - " + v)); // вывод каждого элемента regions в консоль
    }

}
