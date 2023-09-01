# FORECASTING PROJECT 2

This project is subject to the same data requirements as described in the Project 1 handout. Please
refer to that handout for details.

Find a time series data set which seems to show conditional volatility. The time series plot should
show periods of relatively high volatility interspersed with periods of relative calm. It is often easier to
identify conditional volatility if the sampling interval is short (e.g., daily data) than when the sampling
interval is longer. Leave out the last data point (let’s call this then+1’st observation) for the ARMA-
ARCH modeling. You will use it at the end of the project for checking the performance of the forecast
intervals. Using firstn observations of your data set, carry out the same type of analysis as you did in
Homework 8. Break your analysis into nine parts, just as in Homework 8.

Of course, there may be a few modifications to the steps you took in Homework 8, depending on
what you see here. For example, in part 2, you may or may not decide to used=1. Also in part 2, if
you are in fact usingd=1, you may or may not decide to fit a constant term in the ARIMA model.
(You can make this decision on the basis of the time series plot alone, if you wish.)

Finally, check whether either or both of the one-step-ahead forecast intervals calculated in part 5
actually contained then+1’st observation. Based on this, does the ARMA only interval seem too wide,
too narrow, or just about right? Then answer the same question for the ARMA-ARCH interval.

Please try to keep the length of the project to ten pages or less. As always, if you have any ques-
tions, feel free to see me.


