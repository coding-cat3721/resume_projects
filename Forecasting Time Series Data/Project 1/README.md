# FORECASTING PROJECT 1

Use ARIMA methods to forecast a time series data set of your choice, subject to the requirements
below. The data set should have at least 50 observations. It would be a good idea to show me a plot
before you start the project to avoid potential data difficulties. Please provide a source for the data (web
link, but not the course website). The last observation should be recent, and must be the most up-to-date
observation available from the source.

Start by plotting the data. Briefly describe any patterns you see. Decide whether you need to take
logs. If the data are clearly seasonal, e.g., monthly sales of beer, or if the plot seems to indicate a strong
seasonal pattern, then it would be best to remove this component. (It can be put back in at the end). The
easiest way to remove a seasonal component is to subtract out the seasonal averages. For example, if
your data is monthly, subtract from each data value the average for the corresponding month.

Use the ACF, PACF andAICCto select an ARIMA model, and to decide whether a constant term
should be included in the model.

Present the parameter estimate printout for the selected model. Write the complete form of the
fitted model. (You SHOULD NOT give the printouts for the other models you tried. A table of the
AICCvalues for all models will be sufficient. Furthermore, I do not need to see a printout of the data
set. A plot is much more informative). Note that in Minitab, the MA parameters are -1 times the MA
parameters as defined in class. For example, in an MA(1) model in Minitab, if the MA 1 parameter is
-.9, the model isxt= εt+. 9 εt− 1.

For the selected model, comment on the Ljung-Box statistics, plot the residuals and the ACF and
PACF of the residuals. Comment briefly on any problems revealed by this diagnostic checking.

Plot the data, together with the forecasts (at lead times 1-50 or further, if possible), and the 95%
forecast intervals. If you can’t get the computer to put the intervals on the plot, then just sketch them in
(roughly) by hand. Comment briefly on whether the forecasts seem reasonable, and on whether the fore-
cast intervals seem excessively wide.

Length of Project: Please be brief. Just tell me what you did and why. Long explanations are not needed. I think you should be able to do this project quite adequately with 5 pages of text, not including figures and tables. Please don’t submit a huge number of figures and tables. I would hope that the whole project, including figures and tables will be at most 10 pages. This is not an absolute limit, but there is really no advantage to be gained by exceeding it.

If you need help: I will be happy to talk to you about your project, after class or in my office.

