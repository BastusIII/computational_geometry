%Plots the measured times.
max_time = 119.2;
exponents=[1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7];
dimensions=[2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8];
timez=[6.4e-05, 0.000104, 0.000293, 0.002634, 0.01555, 0.1469, 1.482, 8.6e-05, 0.000249, 0.000982, 0.003355, 0.02926, 0.2821, 2.754, 0.000106, 0.000733, 0.005644, 0.01876, 0.1059, 1.673, 7.905, 0.000109, 0.005402, 0.0447, 0.2476, 1.31, 4.841, 33.78, 0.00019, 0.03059, 0.7075, 5.27, 26.03, 119.2, max_time, 0.000137, 0.189, 6.458, 88.59, max_time, max_time, max_time, 0.000108, 0.6282, 82.42, max_time, max_time, max_time, max_time];
%Reshaping vectors for display in graph.
exponents = reshape(exponents,7,7)';
dimensions = reshape(dimensions,7,7)';
timez = reshape(timez,7,7)';
%Create plot.
fig = figure('Name','Times for qhull','NumberTitle','off');
mesh(exponents,dimensions,timez);
xlabel('Number of points(10^x)')
ylabel('Dimensions points')
zlabel('Time taken in seconds')
saveas(fig, 'plot_times.jpg');
