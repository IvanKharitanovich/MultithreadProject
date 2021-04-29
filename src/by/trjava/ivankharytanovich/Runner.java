package by.trjava.ivankharytanovich;

import by.trjava.ivankharytanovich.entity.Truck;
import by.trjava.ivankharytanovich.exception.DataException;
import by.trjava.ivankharytanovich.util.parser.Parser;
import by.trjava.ivankharytanovich.util.parser.impl.TruckParser;
import by.trjava.ivankharytanovich.util.reader.Reader;
import by.trjava.ivankharytanovich.util.reader.impl.ReaderFromFile;
import by.trjava.ivankharytanovich.util.starter.Starter;
import by.trjava.ivankharytanovich.util.starter.impl.TruckStarter;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws DataException {
        Reader reader = new ReaderFromFile();
        Parser parser = new TruckParser();
        Starter starter = new TruckStarter();
        List<String> strings = reader.read("TruckData.txt");
        List<Truck> trucks = new ArrayList<>();

        for (String data : strings) {
            Truck truck = ((Truck) parser.parse(data));
            trucks.add(truck);
        }
        starter.start(trucks);
    }
}
