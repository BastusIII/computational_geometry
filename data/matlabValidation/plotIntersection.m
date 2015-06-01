function [] = plotIntersection(x1, y1, x2, y2, given, expected, status)
    figure
    plot(x1, y1, 'red', 'LineWidth',1.5);
    hold on
    plot(x2, y2, 'blue');
    hold on
    leg = ['line1'; 'line2'];
    tit = {strcat('[','(',num2str(x1(1)),',',num2str(y1(1)),')','|', '(',num2str(x1(2)),',', num2str(y1(2)),')',']X[','(',num2str(x2(1)),',',num2str(y2(1)),')','|', '(',num2str(x2(2)),',', num2str(y2(2)),')]'), status};
    index = 3;
    if ~isempty(given)
        scatter(given(1),given(2),30,'+','MarkerEdgeColor',[.5 0 .5],...
              'LineWidth',1.5);
        hold on
        leg(index, :) = 'given';
        tit(index) = cellstr(strcat('given: (',num2str(given(1)),',',num2str(given(2)),')'));
        index = 4;
    end
    if ~isempty(expected)
        scatter(expected(1), expected(2), 30, 'x', 'MarkerEdgeColor',[0 .5 .5],...
          'LineWidth',1.5)
         hold on
         leg(index, :) = 'expec';
         tit(index) = cellstr(strcat('expected: (',num2str(expected(1)),',',num2str(expected(2)),')'));
    end
    lege = cellstr(leg);
    legend(lege{:});
    title(tit);
end
