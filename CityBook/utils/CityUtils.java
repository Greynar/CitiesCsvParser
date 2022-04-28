package CityBook.utils;

import CityBook.model.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CityUtils {

    /**
     * Чтение указанного файла в список городов
     *
     * @param path путь к файлу с данными
     * @return список городов
     */
    public static List<City> parse(String path) {
        List<City> cities = new ArrayList<>();
        int index = 0;
        try {
            Scanner scannerLine = new Scanner(new File(path));
            while(scannerLine.hasNextLine()){
                 cities.add(parseLineToCity(scannerLine.nextLine()));
            }
            scannerLine.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Разбор входной строки на объект
     *
     * @param line строка с данными о городе
     * @return {@link Etalon.City}
     */
    private static City parseLineToCity(String line){
        City city = new City();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d+");
        city.setName(scanner.next());
        city.setRegion(scanner.next());
        city.setDistrict(scanner.next());
        city.setPopulation(Integer.parseInt(scanner.next()));
        if (scanner.hasNext()) city.setFoundation(scanner.next());
        else city.setFoundation(null);

        scanner.close();

        return city;
    }

    /**
     * Сортировка списка городов по наименованию в алфавитном порядке по убыванию
     * без учета регистра, с использованием lambda-выражений
     *
     * @param cities список городов
     */
    public static void sortByName(List<City> cities) {
        cities.sort((City o1, City o2) -> o1.getRegion().compareToIgnoreCase(o2.getRegion()));
    }

    /**
     * Сортировка списка городов по федеральному округу и наименованию города
     * внутри каждого федерального округа в алфавитном порядке по убыванию
     * с учетом регистра с использованием lambda-выражений и Method Reference
     *
     * @param cities список городов
     */
    public static void sortByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    /**
     * Сортировка списка городов по федеральному округу и наименованию города
     * внутри каждого федерального округа в алфавитном порядке по убыванию
     * с учетом регистра, c использованием java.util.Comparator
     * @param cities список городов
     */
    public static void sortByDistrictsAndNameV2(List<City> cities) {
        cities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                int result = o1.getDistrict().compareTo(o2.getDistrict());
                if (result == 0)
                    return o1.getName().compareTo(o2.getName());
                return result;
            }
        });
    }

    /**
     * Получение индекса элемента массива с наибольшим количеством жителей города
     * методом перебора массива
     *
     * @param cities список городов
     */
    public static void getMaxPopulation(List<City> cities) {
        City [] arrCities = cities.toArray(new City[0]);
        int maxPopulation = 0;
        int index = 0;
        for (int i = 0; i < arrCities.length; i++) {
            if (arrCities[i].getPopulation() > maxPopulation) {
                maxPopulation = arrCities[i].getPopulation();
                index = i;
            }
        }
        System.out.printf("[%d] = %d", index, maxPopulation);
    }

    /**
     * Получение города с наибольшим количеством жителей города используя lambda-выражения
     *
     * @param cities список городов
     */
    public static void getMaxPopulationV2(List<City> cities) {
        System.out.println(cities.stream().max(Comparator.comparing(City::getPopulation)));
    }

    public static void print(List<City> cities){
        cities.forEach(System.out::println);
    }
}
