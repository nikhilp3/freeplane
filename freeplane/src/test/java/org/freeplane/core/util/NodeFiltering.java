package org.freeplane.core.util;

import org.freeplane.api.Node;
import org.freeplane.core.io.ReadManager;
import org.freeplane.core.io.WriteManager;
import org.freeplane.core.resources.TranslatedObject;
import org.freeplane.features.map.MapController;
import org.freeplane.features.map.NodeModel;
import org.freeplane.features.mode.ModeController;
import org.freeplane.features.text.NodeTextConditionController;
import org.freeplane.features.text.TextController;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NodeFiltering {

    @Test
    public void testControllerHandlesNodeID() {
        NodeTextConditionController controller = new NodeTextConditionController();
        TranslatedObject translatedObject = TranslatedObject.literal(TextController.FILTER_NODE_ID);
        Assert.assertTrue(controller.canHandle(translatedObject));
    }

    @Test
    public void testNodeIdText() {
        MapController map = mock(MapController.class);
        when(map.getReadManager()).thenReturn(new ReadManager());
        when(map.getWriteManager()).thenReturn(new WriteManager());
        ModeController modeController = mock(ModeController.class);
        when(modeController.getMapController()).thenReturn(map);
        TextController controller = new TextController(modeController);
        NodeModel model = mock(NodeModel.class);

        when(model.getID()).thenReturn(null);
        Assert.assertEquals(controller.getNodeIdText(model), "");

        when(model.getID()).thenReturn("ID_123");
        Assert.assertEquals(controller.getNodeIdText(model), "ID_123");
    }
}
