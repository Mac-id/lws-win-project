<script>
import { onMount } from 'svelte';
import { weatherData } from './weatherStore.js';

<<<<<<< HEAD
  import OvercastClouds from "./assets/fill/all/overcast.svg";
  import FewClouds from "./assets/fill/all/partly-cloudy-day.svg";
  import ThunderstormsDayRain from "./assets/fill/all/thunderstorms-day-rain.svg";
  import ThunderstormsRain from "./assets/fill/all/thunderstorms-rain.svg";
  import ThunderstormsNightRain from "./assets/fill/all/thunderstorms-night-rain.svg";
  import ThunderstormsDay from "./assets/fill/all/thunderstorms-day.svg";
  import Thunderstorms from "./assets/fill/all/thunderstorms.svg";
  import ThunderstormsNight from "./assets/fill/all/thunderstorms-night.svg";
  import Drizzle from "./assets/fill/all/drizzle.svg";
  import Rain from "./assets/fill/all/rain.svg";
  import Sleet from "./assets/fill/all/sleet.svg";
  import Snow from "./assets/fill/all/snow.svg";
  import Mist from "./assets/fill/all/mist.svg";
  import Smoke from "./assets/fill/all/smoke.svg";
  import Haze from "./assets/fill/all/haze.svg";
  import Dust from "./assets/fill/all/dust.svg";
  import Fog from "./assets/fill/all/fog.svg";
  import Wind from "./assets/fill/all/wind.svg";
  import Tornado from "./assets/fill/all/tornado.svg";
  import ClearDay from "./assets/fill/all/clear-day.svg";
  import Temp from "./assets/fill/all/thermometer.svg";
  import TempMin from "./assets/fill/all/thermometer-colder.svg";
  import TempMax from "./assets/fill/all/thermometer-warmer.svg";
  import Humidity from "./assets/fill/all/humidity.svg";

  let city = "";
  let selectedCity = "Stadt";
  let valid;
  let darkMode = false;

  onMount(() => {
    const theme = darkMode ? "dark" : "light";
    document.documentElement.setAttribute("data-bs-theme", theme);
    // Jetzt rufe die Wetterdaten ab
    fetchWeatherData(selectedCity);
  });

  function addCity() {
    if (!city.trim()) {
      valid = false;
    } else {
      valid = true;
      selectedCity = city;
      fetchWeatherData(selectedCity);
      city = "";
=======
let city = 'Berlin';
let loading = false;
let error = null;

async function fetchWeather() {
    loading = true;
    error = null;
    try {
        const response = await fetch(`http://localhost:8080/api/weather/forecast/${city}`);
        if (!response.ok) throw new Error('Failed to fetch weather data');
        const data = await response.json();
        weatherData.set(data);
    } catch (e) {
        error = e.message;
    } finally {
        loading = false;
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e
    }
}

<<<<<<< HEAD
  const toggleDarkMode = () => {
    darkMode = !darkMode;
    document.documentElement.setAttribute(
            "data-bs-theme",
            darkMode ? "dark" : "light"
    );
  };

  const getWeekday = (dateString) => {
    const date = new Date(dateString);
    return new Intl.DateTimeFormat("de-DE", { weekday: "long" }).format(date);
  };


  const weatherSymbols = {
    clearsky: ClearDay,
    drizzle: Drizzle,
    drizzlerain: Drizzle,
    dust: Dust,
    extremerain: Rain,
    fewclouds: FewClouds,
    fog: Fog,
    freezingrain: Sleet,
    haze: Haze,
    heavyintensitydrizzle: Drizzle,
    heavyintensitydrizzlerain: Drizzle,
    heavyintensityrain: Rain,
    heavyintensityshowerrain: Rain,
    heavyshowerrainanddrizzle: Rain,
    heavyshowersnow: Snow,
    heavysnow: Snow,
    heavythunderstorm: ThunderstormsNight,
    lightintensitydrizzle: Drizzle,
    lightintensitydrizzlerain: Drizzle,
    lightintensityshowerrain: Rain,
    lightrain: Rain,
    lightrainandsnow: Sleet,
    lightshowersleet: Sleet,
    lightshowersnow: Snow,
    lightsnow: Snow,
    lightthunderstorm: ThunderstormsDay,
    mist: Mist,
    moderaterain: Rain,
    overcastclouds: OvercastClouds,
    raggedshowerrain: Rain,
    raggedthunderstorm: Thunderstorms,
    rainandsnow: Sleet,
    sand: Dust,
    sanddustwhirls: Dust,
    showerdrizzle: Drizzle,
    showerrain: Rain,
    showerrainanddrizzle: Rain,
    showersleet: Sleet,
    showersnow: Snow,
    sleet: Sleet,
    smoke: Smoke,
    snow: Snow,
    squalls: Wind,
    thunderstorm: Thunderstorms,
    thunderstormwithdrizzle: ThunderstormsRain,
    thunderstormwithheavydrizzle: ThunderstormsNightRain,
    thunderstormwithheavyrain: ThunderstormsNightRain,
    thunderstormwithlightdrizzle: ThunderstormsDayRain,
    thunderstormwithlightrain: ThunderstormsDayRain,
    thunderstormwithrain: ThunderstormsRain,
    tornado: Tornado,
    veryheavyrain: Rain,
    volcanicash: Smoke,


  };


</script>

<div class="d-flex flex-column align-items-center my-3 rounded">
  <div class="container w-75">
    <div class="card-body">
      <h2 class="card-title text-center fs-2 my-5 fw-semibold">
        Wetter Informationen
      </h2>
      <div class="input-group mb-3">
        <input
                type="text"
                class="form-control {valid ? 'is-valid' : 'is-invalid'}"
                placeholder="Deine Stadt..."
                bind:value={city}
                on:keydown={(e) => e.key === "Enter" && addCity()}
        />
        <button
                class="btn {valid ? 'btn-outline-primary' : 'btn-outline-danger'}"
                type="button"
                on:click={addCity}
        >
          Suchen
        </button>
        <button
                class="btn btn-outline-secondary"
                type="button"
                on:click={toggleDarkMode}
        >
          {darkMode ? "☀️" : "🌙"}
        </button>
      </div>
      <div
              id="carouselExampleControls"
              class="carousel slide {darkMode ? 'pulse-dark' : 'pulse-light'}"
              data-bs-interval="10000"
              data-bs-ride="carousel"
      >
        <div class="carousel-inner">
          {#each $weatherData.forecast as { date, temperature, humidity, weather }, index}
            <div class="carousel-item {index === 0 ? 'active' : ''}">
              <div class="card text-center border-0">
                <div class="card-header">
                  <h2 class="card-title">{getWeekday(date)}</h2>
                  <p class="card-subtitle text-muted">{date}</p>
                </div>
                <img
                        src={weatherSymbols[weather]}
                        class="card-img-top mx-auto w-50"
                        alt="Weather Icon"
                />
                <div class="card-body">
                  <h4 class="card-title mb-3">{selectedCity}</h4>
                  <div class="d-flex flex-column align-items-center">
                    <div
                            class="d-flex align-items-center mb-2 justify-content-center"
                    >
                      <img src={Temp} class="icon me-2" alt="Temperatur" />
                      <h3 class="mb-0">{temperature.current}°C</h3>
                    </div>
                    <div
                            class="d-flex align-items-center mb-2 justify-content-center"
                    >
                      <img
                              src={TempMax}
                              class="icon me-2"
                              alt="Max Temperatur"
                      />
                      <h3 class="mb-0">{temperature.max}°C</h3>
                    </div>
                    <div
                            class="d-flex align-items-center mb-2 justify-content-center"
                    >
                      <img
                              src={TempMin}
                              class="icon me-2"
                              alt="Min Temperatur"
                      />
                      <h3 class="mb-0">{temperature.min}°C</h3>
                    </div>
                    <div
                            class="d-flex align-items-center justify-content-center"
                    >
                      <img
                              src={Humidity}
                              class="icon me-2"
                              alt="Luftfeuchtigkeit"
                      />
                      <h3 class="mb-0">{humidity}%</h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          {/each}
        </div>
        <button
                class="carousel-control-prev"
                type="button"
                data-bs-target="#carouselExampleControls"
                data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button
                class="carousel-control-next"
                type="button"
                data-bs-target="#carouselExampleControls"
                data-bs-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
  </div>
</div>

<style>
  .card-body img {
    width: 10vw;
  }

  .card-body h3 {
    margin-right: 7.5%;
  }

  .carousel-control-prev-icon,
  .carousel-control-next-icon {
    filter: invert(1);
  }

  :root[data-bs-theme="dark"] .carousel-control-prev-icon,
  :root[data-bs-theme="dark"] .carousel-control-next-icon {
    filter: invert(0);
  }

  /* Karten-Stil */
  .card {
    background-color: var(--card-bg-color);
    color: var(--card-text-color);
  }

  #carouselExampleControls {
    box-shadow: var(--box-shadow);
  }

  .slide {
    box-shadow: var(--box-shadow);
  }

  h3,
  h4,
  h5,
  h6 {
    font-size: 1.5rem;
    font-weight: bold;
  }

  h2 {
    font-size: 1.5rem;
    font-weight: bold;
  }

  p {
    font-size: 1.25rem;
  }

  input {
    height: 2.875rem;
  }

  h2 {
    font-size: 1.75rem;
  }

  input {
    font-size: 1.15rem;
  }

  :root[data-bs-theme="light"] {
    --body-bg-color: #ffffff;
    --body-text-color: #000000;
    --card-bg-color: #f8f9fa;
    --card-text-color: #000000;
    --card-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    --box-shadow: rgba(0, 0, 0, 0.2) 0px 12px 28px 0px,
    rgba(0, 0, 0, 0.1) 0px 2px 4px 0px,
    rgba(255, 255, 255, 0.05) 0px 0px 0px 1px inset;
  }

  :root[data-bs-theme="dark"] {
    --body-bg-color: #121212;
    --body-text-color: #ffffff;
    --card-text-color: #ffffff;
    --card-shadow: 0 4px 8px rgba(255, 255, 255, 0.1);
    --box-shadow: rgba(255, 255, 255, 0.2) 0px 12px 28px 0px,
    rgba(255, 255, 255, 0.1) 0px 2px 4px 0px,
    rgba(255, 255, 255, 0.05) 0px 0px 0px 1px inset;
  }

  .pulse-light {
    animation: pulse-light 3s cubic-bezier(0.4, 0, 0.6, 1) infinite;
  }

  .pulse-dark {
    animation: pulse-dark 3s cubic-bezier(0.4, 0, 0.6, 1) infinite;
  }

  @keyframes pulse-light {
    0% {
      box-shadow: var(--box-shadow);
    }
    50% {
      box-shadow:
              rgba(0, 0, 0, 0.3) 0px 20px 80px 0px,
              rgba(0, 0, 0, 0.1) 0px 4px 8px 0px,
              rgba(255, 255, 255, 0.1) 0px 0px 0px 2px inset;
    }
    100% {
      box-shadow: var(--box-shadow);
    }
  }

  @keyframes pulse-dark {
    0% {
      box-shadow: var(--box-shadow);
    }
    50% {
      box-shadow:
              rgba(255, 255, 255, 0.2) 0px 20px 80px 0px,
              rgba(255, 255, 255, 0.1) 0px 4px 8px 0px,
              rgba(255, 255, 255, 0.1) 0px 0px 0px 2px inset;
    }
    100% {
      box-shadow: var(--box-shadow);
    }
  }
</style>
=======
onMount(fetchWeather);
</script>

<main>
    <input bind:value={city} on:change={fetchWeather}>
    {#if loading}
        <p>Loading...</p>
    {:else if error}
        <p>Error: {error}</p>
    {:else}
        {#each $weatherData as day}
            <div>
                <h3>{day.forecastDate}</h3>
                <p>Temperature: {day.temperature}°C</p>
                <p>Humidity: {day.humidity}%</p>
                <p>{day.description}</p>
            </div>
        {/each}
    {/if}
</main>
>>>>>>> d65976157d5658f8207d9f248298e3c3330ebb4e
