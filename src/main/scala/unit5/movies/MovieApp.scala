package unit5.movies

import PrintResultHelpers._

object MovieApp extends App {
  val dataset = new MoviesDataset("movies_metadata.csv")
  val movies = dataset.movies
  private val unknown = "--"

  //TODO:
  // How would you count how many movies had no release date ?
  // Can you count how many Italian movies it contains? What about German films?
  // Can you list all the genres available in the data set?
  // Which movie is the most recent one?
  // What is the duration of the longest film?
  // Which of them made the smallest profit?

  printResult(
    question = "How many movies are there in the Dataset?",
    answer = {
      val totalCount = movies.size
      s"$totalCount movies"
    }
  )

  printResult(
    question = "How many movies were released in 1987?",
    answer = {
      val countFrom1987 = movies.count(
        _.releaseDate.exists(_.getYear == 1987))
      s"$countFrom1987 movies"
    }
  )

  printResult(
    question = "TOP 5 movies per vote average and count",
    answers = {
      val topPerVote =
        movies.filter(_.voteCount >= 50)
          .sortBy { movie =>
            (-movie.voteAverage, -movie.voteCount)
          }.take(5)
      topPerVote.map { movie =>
        s"[AVG: ${movie.voteAverage}, COUNT: ${movie.voteCount}] " +
          s"${movie.title}"
      }
    }
  )

  printResult(
    question = "TOP 5 movies per popularity",
    answers = {
      val topPerPopularity =
        movies.sortBy { movie =>
          -movie.popularity.getOrElse(0f)
        }.take(5)
      topPerPopularity.map { movie =>
        s"[POPULARITY: ${movie.popularity.getOrElse(unknown)}] " +
          s"${movie.title}"
      }
    }
  )

  printResult(
    question = "5 non-english movies",
    answers = {
      val topNonEnglishPerPopularity =
        movies.filterNot(_.originalLanguage == "en")
          .take(5)
      topNonEnglishPerPopularity.map { movie =>
        s"[LANG: ${movie.originalLanguage}, " +
          s"RELEASE DATE: ${movie.releaseDate.getOrElse(unknown)}] " +
          s"${movie.title} (${movie.originalTitle})"
      }
    }
  )

  printResult(
    question = "Which movie made the most profit?",
    answer = {
      val mostProfit = movies.maxBy(
        movie => movie.revenue - movie.budget)
      val formattedProfit = {
        val formatter = java.text.NumberFormat.getInstance()
        formatter.format(mostProfit.revenue - mostProfit.budget)
      }
      s"[PROFIT: USD $formattedProfit] ${mostProfit.title}"
    }
  )
}
