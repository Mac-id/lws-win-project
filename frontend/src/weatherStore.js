import { writable } from "svelte/store";

export const weatherData = writable({
  city: "",
  forecast: [],
});

export const fetchWeatherData = async (city) => {
  try {
    const response = await fetch(
        `http://localhost:8080/api/weather/forecast/${city}`
    );
    const data = await response.json();

    // Umformatierung der API-Daten in das gewünschte Format
    const forecast = data.map((forecastItem) => ({
      date: forecastItem.forecastDate,
      temperature: {
        current: parseFloat(forecastItem.temperature.toFixed(1)),
        min: parseFloat(forecastItem.minTemperature.toFixed(1)),
        max: parseFloat(forecastItem.maxTemperature.toFixed(1)),
      },
      humidity: parseFloat(forecastItem.humidity.toFixed(1)),
      weather: forecastItem.description.toLowerCase().replace(" ", ""),
    }));

    weatherData.set({
      city: city,
      forecast: forecast,
    });
  } catch (error) {
    console.error("Fehler beim Abrufen der Wetterdaten:", error);
  }
};
