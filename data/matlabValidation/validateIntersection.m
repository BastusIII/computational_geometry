function [] = validateIntersection(x1, y1, x2, y2, testCase, doPlot, terminateAtFail)
    [x_intersect,y_intersect] = polyxpoly(x1, y1, x2, y2);
    try
        testCase.assertThat([x_intersect y_intersect], ~matlab.unittest.constraints.IsEmpty())
        if doPlot >= 2
            plotIntersection(x1, y1, x2, y2, [], [x_intersect,y_intersect], 'success');
        end
    catch err
        if doPlot >= 1
            plotIntersection(x1, y1, x2, y2, [], [x_intersect,y_intersect], 'fail');
            if terminateAtFail
                    rethrow(err);
            end
        end
    end
end
