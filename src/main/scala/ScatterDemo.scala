package org.jzy3d.demos.scatter

import java.util.Random

import org.jzy3d.analysis.AbstractAnalysis
import org.jzy3d.analysis.AnalysisLauncher
import org.jzy3d.chart.factories.AWTChartComponentFactory
import org.jzy3d.colors.Color
import org.jzy3d.maths.Coord3d
import org.jzy3d.plot3d.primitives.Scatter
import org.jzy3d.plot3d.rendering.canvas.Quality


class ScatterDemo extends AbstractAnalysis{

	def init: Unit = {
		val size: Int = 500000

    val a: Float = 0.25.toFloat

    val r: Random = new Random()
    r.setSeed(0)

    // generate points amd colors randomly
    def genPointsAndColors: (Array[Coord3d], Array[Color]) = {
      var pointsList: List[Coord3d] = Nil
      var colorsList: List[Color] = Nil

      for (i <- 0 to size) {
        val x: Float = r.nextFloat() - 0.5f
        val y: Float = r.nextFloat() - 0.5f
        val z: Float = r.nextFloat() - 0.5f

        val point = new Coord3d(x, y, z)
        val color = new Color(x, y, z, a)
        pointsList = point :: pointsList
        colorsList = color :: colorsList
      }
      (pointsList.reverse.toArray, colorsList.reverse.toArray)
    }

    val (points, colors) = genPointsAndColors

    val scatter: Scatter = new Scatter(points, colors)
    chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt")
    chart.getScene().add(scatter)
	}
}

object ScatterDemo{
  def main(args: Array[String]) = {
    AnalysisLauncher.open(new ScatterDemo())
  }
}
